package org.semspringframework.context;

import org.junit.Test;
import org.semspringframework.context.event.ContextStartedEvent;
import org.semspringframework.context.support.SimpleApplicationEventPublisher;

public class EventPublisherTest {

    @Test
    public void test0() {
        SimpleApplicationEventPublisher eventPublisher = new SimpleApplicationEventPublisher();

        eventPublisher.getEventMulticaster().addApplicationListener(new ContextStartedListener());

        eventPublisher.publishEvent(new ContextStartedEvent("haha"));
    }

}
