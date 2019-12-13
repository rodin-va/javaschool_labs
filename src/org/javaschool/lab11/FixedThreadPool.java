package org.javaschool.lab11;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class FixedThreadPool implements ThreadPool {
    public FixedThreadPool(Integer threadCount) {
        this.threadCount = threadCount;
        this.threads = new Thread[threadCount];

        for (int i = 0; i < this.threadCount; i++) {
            threads[i] = new Thread();
        }
    }

    private void processTask() {
        for (int i = 0; i < this.threadCount; i++) {
            if(threads[i].getState().equals(Thread.State.TERMINATED)) {
                //threads[i]. runnableDeque.pop()
            }
        }
    }

    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void start() {
        this.running = true;
    }

    @Override
    public void execute(Runnable runnable) {
        runnableDeque.push(runnable);

        if(this.isRunning()) {

        }
    }

    private Deque<Runnable> runnableDeque = new LinkedBlockingDeque<>();
    private Integer threadCount;
    private Thread[] threads;
    private boolean running;
}
