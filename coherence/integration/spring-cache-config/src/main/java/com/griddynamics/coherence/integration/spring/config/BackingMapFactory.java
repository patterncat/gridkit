package com.griddynamics.coherence.integration.spring.config;

import com.tangosol.net.BackingMapManagerContext;
import com.tangosol.util.ObservableMap;

/**
 * @author Dmitri Babaev
 */
public interface BackingMapFactory {
	ObservableMap newBackingMap(BackingMapManagerContext context);
}
