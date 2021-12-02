package org.semspringframework.context.event;

import org.semspringframework.context.ApplicationEvent;
import org.semspringframework.context.ApplicationListener;

import java.util.List;
import java.util.concurrent.Executor;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    private Executor executor;

    public SimpleApplicationEventMulticaster() {
    }

    public SimpleApplicationEventMulticaster(Executor executor) {
        this.executor = executor;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        if(null != this.executor)
            executorMulticastEvent(event);
        else
            simpleMulticastEvent(event);
    }

    /**
     * multi-thread execute function of listener named 'onApplicationEvent' if the event is listened by the listener.
     * @param event
     */
    private void executorMulticastEvent(ApplicationEvent event) {

        List<ApplicationListener<?>> applicationListeners = getApplicationListeners();

        for (ApplicationListener<?> applicationListener : applicationListeners) {
            if(applicationListener.judgeEvent(event.getClass())) {
                this.executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        applicationListener.onApplicationEvent(event);
                    }
                });
            }
        }

    }

    /**
     * single-thread execute function of listener named 'onApplicationEvent' if the event is listened by the listener.
     * @param event
     */
    private void simpleMulticastEvent(ApplicationEvent event) {

        List<ApplicationListener<?>> applicationListeners = getApplicationListeners();

        for (ApplicationListener<?> applicationListener : applicationListeners) {
            if(applicationListener.judgeEvent(event.getClass())) {
                applicationListener.onApplicationEvent(event);
            }
        }

    }

}
