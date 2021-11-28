package org.semspringframework.context.event;

/**
 * application context event
 * if the application context implement the function named start, ContextStartedEvent will happen.
 */
public class ContextStartedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextStartedEvent(Object source) {
        super(source);
    }
}
