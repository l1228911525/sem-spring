package org.semspringframework.context.support;

import org.semspringframework.context.ApplicationEvent;
import org.semspringframework.context.ApplicationEventPublisher;
import org.semspringframework.context.event.SimpleApplicationEventMulticaster;

public class SimpleApplicationEventPublisher implements ApplicationEventPublisher {

    private SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

    public SimpleApplicationEventMulticaster getEventMulticaster() {
        if(null == this.eventMulticaster)
            throw new IllegalStateException("application event multicaster is null");
        return this.eventMulticaster;
    }

    public void setEventMulticaster(SimpleApplicationEventMulticaster eventMulticaster) {
        this.eventMulticaster = eventMulticaster;
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.eventMulticaster.multicastEvent(event);
    }

}
