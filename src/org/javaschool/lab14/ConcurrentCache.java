package org.javaschool.lab14;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentCache {
    ConcurrentHashMap

    private HashMap<Object, ReentrantReadWriteLock> lockHashMap = new HashMap<>();
    private HashMap<Object, Object> cacheHashMap = new HashMap<>();
    private ReentrantReadWriteLock mainLock = new ReentrantReadWriteLock();

    public void putValue(Object key, Object value) {
        ReentrantReadWriteLock itemLock;

        mainLock.readLock().lock();
        try {
            itemLock = lockHashMap.get(key);

            if(itemLock == null) {
                mainLock.writeLock().lock();

                try{
                    itemLock = new ReentrantReadWriteLock();
                    lockHashMap.put(key, itemLock);
                } finally {
                    mainLock.writeLock().unlock();
                }
            }
        } finally {
            mainLock.readLock().unlock();
        }

        itemLock.writeLock().lock();
        try {
            cacheHashMap.put(key, value);
        } finally {
            itemLock.writeLock().unlock();
        }

    }

    public Object getValue(Object key) {
        return getValueOrDefault(key, null);
    }

    public Object getValueOrDefault(Object key, Object defaultValue) {
        ReentrantReadWriteLock itemLock;

        //mainLock.readLock().lock();
        try {
            itemLock = lockHashMap.get(key);

            if(itemLock == null) {
                return defaultValue;
            }
        } finally {
            //mainLock.readLock().unlock();
        }

        itemLock.readLock().lock();
        try {
            return cacheHashMap.getOrDefault(key, defaultValue);
        } finally {
            itemLock.readLock().unlock();
        }
    }

    public Object getCachedValue(Object key) {
        ReentrantReadWriteLock itemLock;

        mainLock.readLock().lock();
        try {
            itemLock = lockHashMap.get(key);

            if(itemLock == null) {
                mainLock.writeLock().lock();

                try{
                    itemLock = new ReentrantReadWriteLock();
                    lockHashMap.put(key, itemLock);
                } finally {
                    mainLock.writeLock().unlock();
                }
            }
        } finally {
            mainLock.readLock().unlock();
        }

        itemLock.writeLock().lock();
        try {
            cacheHashMap.put(key, value);
        } finally {
            itemLock.writeLock().unlock();
        }
    }
}
