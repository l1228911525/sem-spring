package org.semspringframework.context.event;

import org.semspringframework.context.ApplicationEvent;
import org.semspringframework.context.ApplicationListener;

import java.util.List;
import java.util.Map;

/**
 * application event multicaster
 * manager application listener and multicast event to listener
 */
public interface ApplicationEventMulticaster {


    /**
     * add a listener into collection of listener
     * @param listener added into collection of listener
     */
    public void addApplicationListener(ApplicationListener<?> listener);

    /**
     * remove a listener from the collection of listener
     * @param listener removed from collection of listener
     */
    public void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * remove a few listeners from collection of listener by event that those listeners monitor
     * @param eventClazz
     */
    public void removeApplicationListenerByType(Class<? extends ApplicationEvent> eventClazz);

    /**
     * remove all listeners
     */
    public void removeAllApplicationListeners();

    /**
     * get all listener
     * @return
     */
    public List<ApplicationListener<?>> getApplicationListeners();

    /**
     * multicast event to listener
     * @param event
     */
    public void multicastEvent(ApplicationEvent event);
}
