package org.javaschool.lab14;

import java.util.ArrayList;

class PutTask implements Runnable {
    ConcurrentCache concurrentCache;

    PutTask(ConcurrentCache cache) {
        this.concurrentCache = cache;
    }

    @Override
    public void run() {
        Long key = Long.valueOf(Math.round(Math.random() * 5));
        Long value = Long.valueOf(Math.round(Math.random() * 5) + 5);

        System.out.println(
            "Thread" + Thread.currentThread().getName()
            + ", Operation: Put, Key: " + key.toString()
            + ", Value: " + value.toString()
        );

        concurrentCache.putValue(key, value);
    }
}

class GetTask implements Runnable {
    ConcurrentCache concurrentCache;

    GetTask(ConcurrentCache cache) {
        this.concurrentCache = cache;
    }

    @Override
    public void run() {
        Long key = Long.valueOf(Math.round(Math.random() * 5));
        Object value = concurrentCache.getValueOrDefault(key, Long.valueOf(-1));
        System.out.println(
                "Thread" + Thread.currentThread().getName()
                + ", Operation: Get, Key: " + key.toString()
                + ", Value: " + value.toString()
        );
    }
}

class CacheTask implements Runnable {
    ConcurrentCache concurrentCache;

    CacheTask(ConcurrentCache cache) {
        this.concurrentCache = cache;
    }

    @Override
    public void run() {
        Long key = Long.valueOf(Math.round(Math.random() * 5));
        Object value = concurrentCache.getValueOrDefault(key, Long.valueOf(-1));
        System.out.println(
                "Thread" + Thread.currentThread().getName()
                        + ", Operation: Get, Key: " + key.toString()
                        + ", Value: " + value.toString()
        );
    }
}

public class CacheMain {
    public static void main(String[] args) {
        ConcurrentCache cache = new ConcurrentCache();
        ArrayList<Thread> threadArrayList = new ArrayList();

        /*Thread tmp;
        for (int i = 0; i < 50; i++) {
            tmp = new Thread(new GetTask(cache));
            //tmp.setPriority(Thread.MAX_PRIORITY);
            threadArrayList.add(tmp);
            tmp.start();

            tmp = new Thread(new PutTask(cache));
            threadArrayList.add(tmp);
            //tmp.setPriority(Thread.MIN_PRIORITY);
            tmp.start();



        }*/


    }
}
