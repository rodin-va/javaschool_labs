package org.javaschool.lab6;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodUtils {
    public static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers())            //public method without parameters
                && method.getParameterTypes().length == 0) {

            if (method.getName().matches("^get[A-Z].*")     //named like get<FieldName> and return non-void type
                    && !method.getReturnType().equals(void.class))
                return true;
            if (method.getName().matches("^is[A-Z].*") &&   //named like is<FieldName> and return boolean type
                    method.getReturnType().equals(boolean.class))
                return true;
        }
        return false;
    }

    public static boolean isSetter(Method method) {
        return Modifier.isPublic(method.getModifiers())             //public method
                && method.getReturnType().equals(void.class)        //return void type
                && method.getParameterTypes().length == 1           //only one parameter
                && method.getName().matches("^set[A-Z].*");  //named like set<FieldName>
    }
}
