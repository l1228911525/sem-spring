package org.semspringframework.context.support;

import org.semspringframework.context.ApplicationEvent;
import org.semspringframework.context.ApplicationEventPublisher;
import org.semspringframework.context.event.SimpleApplicationEventMulticaster;

public class SimpleApplicationEventPublisher implements ApplicationEventPublisher {

    public SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.eventMulticaster.multicastEvent(event);
    }

}
