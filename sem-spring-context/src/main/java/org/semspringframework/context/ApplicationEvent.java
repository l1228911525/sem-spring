package org.semspringframework.context;

import java.util.EventObject;

/**
 * the class is the spring base event. a class extending it represent it is a spring event.
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
