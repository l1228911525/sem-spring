package org.semspringframework.context;

import org.semspringframework.context.event.ApplicationContextListener;
import org.semspringframework.context.event.ContextStartedEvent;

public class ContextStartedListener implements ApplicationContextListener<ContextStartedEvent> {
    @Override
    public Boolean judgeEvent(Class<? extends ApplicationEvent> currentEventCls) {
        return currentEventCls.equals(ContextStartedEvent.class);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Object source = event.getSource();
        System.out.println(source);
    }
}
