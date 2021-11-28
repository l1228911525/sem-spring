package org.semspringframework.context;

import java.util.EventListener;

/**
 * the class monitor ApplicationEvent. if ApplicationEvent happen, onApplicationEvent will be transferred.
 * @param <T> type of ApplicationEvent
 */
public interface ApplicationListener<T extends ApplicationEvent> extends EventListener {

    /**
     * if ApplicationEvent happen, the method will be transferred. the param(event) is the ApplicationEvent.
     * @param event monitored by the class.
     */
    public void onApplicationEvent(T event);

}
