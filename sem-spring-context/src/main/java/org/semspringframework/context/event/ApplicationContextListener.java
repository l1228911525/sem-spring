package org.semspringframework.context.event;

import org.semspringframework.context.ApplicationListener;

/**
 * The class is event of ApplicationContext and occurs when the application context is running.
 * if the event occurs, onApplicationEvent will be executed.
 * @param <T> type of the ApplicationContextEvent or its subclass
 */
public interface ApplicationContextListener<T extends ApplicationContextEvent> extends ApplicationListener<T> {

    /**
     * if ApplicationEvent happen, the method will be transferred. the param(event) is the ApplicationEvent.
     * @param event monitored by the class.
     */
    @Override
    void onApplicationEvent(T event);
}
