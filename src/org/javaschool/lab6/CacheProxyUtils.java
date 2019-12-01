package org.javaschool.lab6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;

class CacheHandler implements InvocationHandler {
    private final Object delegate;
    private HashMap<String, HashMap<Object[], Object>> cacheMethods = new HashMap<>();

    CacheHandler(Object delegate) {
        Class delegateClass = delegate.getClass();
        Arrays.asList(delegateClass.getMethods()).forEach( method -> {
            if (!method.getReturnType().equals(void.class)
                    && method.isAnnotationPresent(Cache.class)
                    && cacheMethods.get(method.getName()) == null) {
                cacheMethods.put(method.getName(), new HashMap<Object[], Object>());
            }
        });
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        HashMap<Object[], Object> cachedValues =  cacheMethods.get(method.getName());
        if(cachedValues != null) {
            System.out.println("Cache for method " + method.getName() + " is exist!");
            result = cachedValues.get(args);
        } else {
            System.out.println("Cache for method " + method.getName() + " doesn't exist!");
        }
        if(result == null) {
            result = method.invoke(delegate, args);
            if(cachedValues != null) {
                cachedValues.put(args, result);
            }
            System.out.println("get result from invoke method:  " + result.toString());
        } else {
            System.out.println("get result from cache: " + result.toString());
        }

        return result;
    }
}


public class CacheProxyUtils {

    public static Object getCacheProxy(Object o) {
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), new CacheHandler(o));
    }
}