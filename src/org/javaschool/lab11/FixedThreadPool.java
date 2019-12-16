package org.javaschool.lab11;

import java.util.Deque;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingDeque;

public class FixedThreadPool implements ThreadPool {
    public FixedThreadPool(Integer threadCount) {
        this.threadCount = threadCount;
        /*this.threads = new Thread[threadCount];

        for (int i = 0; i < this.threadCount; i++) {
            threads[i] = new Thread();
        }*/
    }

    /*private void processTask() {
        for (int i = 0; i < this.threadCount; i++) {
            if(threads[i].getState().equals(Thread.State.TERMINATED)) {
                //threads[i]. runnableDeque.pop()
            }
        }
    }*/
    class ProccessQueueTask extends TimerTask {
        FixedThreadPool threadPool;

        public ProccessQueueTask(FixedThreadPool threadPool) {
            this.threadPool = threadPool;
        }

        @Override
        public void run() {
            threadPool.processQueue();
        }
    };


    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void start() {
        this.timer = new Timer();
        timer.schedule(new ProccessQueueTask(this),1000);
        this.running = true;

    }

    @Override
    public void execute(Runnable runnable) {
        if(!this.isRunning() || !this.addToThread(runnable)) {
            runnableDeque.push(runnable);
        }
    }

    void processQueue() {
        boolean allThreadsBusy = false;
        if(this.isRunning()) {
            while (!runnableDeque.isEmpty() && !allThreadsBusy) {
                if(!this.addToThread(runnableDeque.getFirst())) {
                    allThreadsBusy = true;
                } else {
                    runnableDeque.removeFirst();
                }
            }
        }
    }

    boolean addToThread(Runnable runnable) {
        for (int i = 0; i < this.threadCount; i++) {
            if(threads[i] == null || threads[i].getState().equals(Thread.State.TERMINATED)) {
                threads[i] = new Thread(runnable);
                return true;
            }
        }

        return false;
    }

    private Deque<Runnable> runnableDeque = new LinkedBlockingDeque<>();
    private Integer threadCount;
    private Thread[] threads;
    private boolean running;
    private Timer timer;
}
