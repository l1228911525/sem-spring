package org.semspringframework.context;

/**
 * if a class implement the interface {@link ApplicationEventPublisher} surface it hold ability of publishing application event
 */
public interface ApplicationEventPublisher {

    /**
     * publish application event
     * @param event
     */
    public void publishEvent(ApplicationEvent event);

}
