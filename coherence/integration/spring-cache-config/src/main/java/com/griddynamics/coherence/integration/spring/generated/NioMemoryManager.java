//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.16 at 09:47:17 PM MSD 
//


package com.griddynamics.coherence.integration.spring.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "className",
    "initParams",
    "initialSize",
    "maximumSize"
})
@XmlRootElement(name = "nio-memory-manager")
public class NioMemoryManager {

    @XmlElement(name = "class-name")
    protected ClassName className;
    @XmlElement(name = "init-params")
    protected InitParams initParams;
    @XmlElement(name = "initial-size")
    protected InitialSize initialSize;
    @XmlElement(name = "maximum-size")
    protected MaximumSize maximumSize;

    /**
     * Gets the value of the className property.
     * 
     * @return
     *     possible object is
     *     {@link ClassName }
     *     
     */
    public ClassName getClassName() {
        return className;
    }

    /**
     * Sets the value of the className property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassName }
     *     
     */
    public void setClassName(ClassName value) {
        this.className = value;
    }

    /**
     * Gets the value of the initParams property.
     * 
     * @return
     *     possible object is
     *     {@link InitParams }
     *     
     */
    public InitParams getInitParams() {
        return initParams;
    }

    /**
     * Sets the value of the initParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link InitParams }
     *     
     */
    public void setInitParams(InitParams value) {
        this.initParams = value;
    }

    /**
     * Gets the value of the initialSize property.
     * 
     * @return
     *     possible object is
     *     {@link InitialSize }
     *     
     */
    public InitialSize getInitialSize() {
        return initialSize;
    }

    /**
     * Sets the value of the initialSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link InitialSize }
     *     
     */
    public void setInitialSize(InitialSize value) {
        this.initialSize = value;
    }

    /**
     * Gets the value of the maximumSize property.
     * 
     * @return
     *     possible object is
     *     {@link MaximumSize }
     *     
     */
    public MaximumSize getMaximumSize() {
        return maximumSize;
    }

    /**
     * Sets the value of the maximumSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaximumSize }
     *     
     */
    public void setMaximumSize(MaximumSize value) {
        this.maximumSize = value;
    }

}
