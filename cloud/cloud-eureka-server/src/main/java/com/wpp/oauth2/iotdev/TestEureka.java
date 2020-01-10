package com.wpp.oauth2.iotdev;

import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestEureka {
    ScheduledExecutorService scheduler;
    ThreadPoolExecutor heartbeatExecutor;
    ThreadPoolExecutor cacheRefreshExecutor;

    public TestEureka() {
        this.scheduler = Executors.newScheduledThreadPool(2, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("scheduler:");
            return thread;
        });

        heartbeatExecutor = new ThreadPoolExecutor(
                1, 5, 0, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    thread.setName("heartbeat:");
                    return thread;
                }
        );  // use direct handoff

        cacheRefreshExecutor = new ThreadPoolExecutor(
                1, 5, 0, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    thread.setName("cacheRefresh:");
                    return thread;
                }
        );  // use direct handoff


    }

    public static void main(String[] args) throws InterruptedException {
        new TestEureka().start();

        Thread.sleep(Long.MAX_VALUE);
    }

    private void start() {
        scheduler.schedule(new TimedSupervisorTask(scheduler, heartbeatExecutor, new Task1(), 5), 5, TimeUnit.SECONDS);
    }

    class Task1 implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this);
        }
    }
}

class TimedSupervisorTask extends TimerTask {
    ScheduledExecutorService scheduler;
    ThreadPoolExecutor executor;
    Runnable task;
    int timeout;
    AtomicInteger successCounter;
    AtomicInteger failCounter;

    public TimedSupervisorTask(ScheduledExecutorService scheduler, ThreadPoolExecutor executor, Runnable task, int timeout) {
        this.scheduler = scheduler;
        this.executor = executor;
        this.task = task;
        this.timeout = timeout;
        this.successCounter = new AtomicInteger(0);
        this.failCounter = new AtomicInteger(0);
    }

    @Override
    public void run() {
        try {
            Future<?> submit = executor.submit(task);
            System.out.println("success：" + successCounter.get());
            System.out.println("fail：" + failCounter.get());
            submit.get(timeout, TimeUnit.SECONDS);
            successCounter.incrementAndGet();
        } catch (Throwable e) {
            e.printStackTrace();
            failCounter.incrementAndGet();
        } finally {
            scheduler.schedule(this, 10, TimeUnit.SECONDS);
        }
    }
}
