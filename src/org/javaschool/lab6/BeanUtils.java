package org.javaschool.lab6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Class classTo = to.getClass();
        Class classFrom = from.getClass();

        Arrays.asList(classFrom.getMethods()).forEach(method -> {
            if (MethodUtils.isGetter(method)) {
                try {
                    String setterName = method.getName().replaceFirst("^(get|is)", "set");
                    Class[] parameterList = new Class[1];
                    Class returnType = method.getReturnType();
                    Method setterMethod = null;

                    while (setterMethod == null && returnType != null) {
                        parameterList[0] = returnType;

                        try {
                            setterMethod = classTo.getMethod(setterName, parameterList);
                        } catch (NoSuchMethodException e) {
                            returnType = returnType.getSuperclass();
                        }
                    }

                    if (setterMethod != null && MethodUtils.isSetter(setterMethod)) {
                        setterMethod.invoke(to, method.invoke(from));
                    }
                } catch (InvocationTargetException | IllegalAccessException | SecurityException | NullPointerException e) {
                    //do nothing, skip element
                }
            } //if (MethodUtils.isGetter(method))
        }); // Arrays.asList(classFrom.getMethods()).forEach(method -> {
    } // public static void assign(Object to, Object from) {
}
