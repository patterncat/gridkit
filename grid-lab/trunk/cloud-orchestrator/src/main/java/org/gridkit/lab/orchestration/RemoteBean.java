package org.gridkit.lab.orchestration;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.gridkit.lab.orchestration.BeanProxy.Argument;
import org.gridkit.lab.orchestration.BeanRegistry.BeanRef;

public abstract class RemoteBean implements SourceAction, Serializable {        
    private static final long serialVersionUID = -6430323142684195091L;
    
    protected BeanRef ref;
    protected Scope scope;
    protected Class<?> clazz;
    protected StackTraceElement createPoint;
    
    public RemoteBean(Class<?> clazz, BeanRef ref, Scope scope, StackTraceElement createPoint) {
        this.ref = ref;
        this.scope = scope;
        this.clazz = clazz;
        this.createPoint = createPoint;
    }
        
    public static class Deploy extends RemoteBean implements ViNodeAction, Callable<Void> {
        private static final long serialVersionUID = -488868113954566425L;
        
        protected Object prototype;
        
        public Deploy(Object prototype, BeanRef ref, Scope scope, StackTraceElement createPoint) {
            super(prototype.getClass(), ref, scope, createPoint);
            this.prototype = prototype;
        }
        
        @Override
        public Callable<Void> getExecutor() {
            return this;
        }

        @Override
        public Void call() {
            BeanRegistry.getInstance().deploy(ref, prototype);
            return null;
        }
        
        @Override
        public String getSource() {
            return "deploy(" + prototype.getClass().getSimpleName() + ")";
        }
    }
    
    public static class Invoke extends RemoteBean implements ViNodeAction, Callable<Void> {
        private static final long serialVersionUID = 613178071091607041L;
        
        //TODO remove to avoid long chain
        protected RemoteBean target;
        protected MethodRef method;
        protected List<BeanProxy.Argument> args;
        protected String source;
        
        public Invoke(BeanRef ref, Scope scope,
                      RemoteBean target, Method method, 
                      List<BeanProxy.Argument> args,
                      StackTraceElement createPoint) {
            super(method.getReturnType(), ref, scope, createPoint);
            this.target = target;
            this.method = new MethodRef(method);
            this.args = args;
            this.source = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
        }
        
        @Override
        public Void call() throws Exception {
            try {
                BeanRegistry.getInstance().invoke(target.ref, method, getRegistryArgs(), ref);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof Exception) {
                    throw (Exception)cause;
                } else {
                    throw e;
                }
            }
            return null;
        }
        
        private List<BeanRegistry.Argument> getRegistryArgs() {
            List<BeanRegistry.Argument> result = new ArrayList<BeanRegistry.Argument>(args.size());
            
            for (BeanProxy.Argument arg : args) {
                if (arg.isRemote()) {
                    result.add(BeanRegistry.Argument.newRef(arg.getRemote().ref));
                } else {
                    result.add(BeanRegistry.Argument.newVal(arg.getValue()));
                }
            }
            
            return result;
        }

        @Override
        public Callable<Void> getExecutor() {
            return this;
        }
        
        @Override
        public String getSource() {
            return source;
        }
    }

    private static class ProxyHandler implements BeanProxy.Handler {
        private RemoteBean bean;
        private Platform platform;
        
        public ProxyHandler(RemoteBean bean, Platform platform) {
            this.bean = bean;
            this.platform = platform;
        }

        @Override
        public Object invoke(Method method, List<Argument> args) {
            validate(args);
            
            RemoteBean.Invoke result = platform.newRemoteBean(
                bean.scope, bean, method, args, ClassOps.stackTraceElement(3)
            );
            
            platform.invoke(result, getRefs(args));

            return result.getProxy(platform);
        }
        
        private List<Object> getRefs(List<BeanProxy.Argument> args) {
            List<Object> result = new ArrayList<Object>();
            
            for (BeanProxy.Argument arg : args) {
                if (arg.isRemote()) {
                    result.add(arg.getRemote().ref);
                }
            }
            result.add(bean.ref);
            
            return result;
        }
        
        private static void validate(List<BeanProxy.Argument> args) {
            for (BeanProxy.Argument arg : args) {
                validate(arg);
            }
        }
        
        private static void validate(BeanProxy.Argument arg) {
            if (arg.isLocal()) {
                throw new IllegalArgumentException();
            } else if (arg.isValue()) {
                boolean valid = arg.getValue() instanceof Serializable || arg.getValue() instanceof Remote;
                if (!valid){
                    throw new IllegalArgumentException();
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Platform platform) {
        return (T)BeanProxy.newInstance(clazz, new ProxyHandler(this, platform));
    }
    
    public Object getId() {
        return ref;
    }
    
    public Scope getScope() {
        return scope;
    }

    @Override
    public StackTraceElement getLocation() {
        return createPoint;
    }
}
