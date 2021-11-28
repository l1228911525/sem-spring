package org.semspringframework.context.event;

/**
 * application context event
 * if application context implement the function named refresh, ContextRefreshEvent will happen.
 */
public class ContextRefreshEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshEvent(Object source) {
        super(source);
    }
}
