package org.javaschool.lab6;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.TreeMap;

class Test extends Object {
    public static final String VALUES = "VALUES";
    public static final String VALUES1 = "VALUES";
    public Test() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class Test2 extends Test {
    //nothing
}

class TestFrom extends Object {
    public String getName() {
        return Name;
    }

    public int getAge() {
        return age;
    }

    public Test getTest() {
        return test;
    }

    public boolean isGoingSchool() {
        return goingSchool;
    }

    public Test2 getSecondTest() {
        return secondTest;
    }

    public Long getHeight() {
        return height;
    }

    public TestFrom(String name, int age, Test test, Test2 secondTest, boolean goingSchool, Long height) {
        Name = name;
        this.age = age;
        this.test = test;
        this.secondTest = secondTest;
        this.goingSchool = goingSchool;
        this.height = height;
    }

    String Name;
    int age;
    Test test;
    Test2 secondTest;
    boolean goingSchool;
    Long height;


    @Override
    public String toString() {
        return "TestFrom{" +
                "Name='" + Name + '\'' +
                ", age=" + age +
                ", test=" + test +
                ", secondTest=" + secondTest +
                ", goingSchool=" + goingSchool +
                ", height=" + height +
                '}';
    }
}

class TestTo extends Object {
    public void setName(String name) {
        Name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTest(Test2 test) {
        this.test = test;
    }

    public void setGoingSchool(boolean goingSchool) {
        this.goingSchool = goingSchool;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setSecondTest(Test secondTest) {
        this.secondTest = secondTest;
    }

    String Name;
    int age;
    Test2 test;
    boolean goingSchool;
    String height;
    Test secondTest;

    @Override
    public String toString() {
        return "TestTo{" +
                "Name='" + Name + '\'' +
                ", age=" + age +
                ", test=" + test +
                ", goingSchool=" + goingSchool +
                ", height='" + height + '\'' +
                ", secondTest=" + secondTest +
                '}';
    }
}

interface Proxyble {
      public String getCachedField();
      public void setCachedField(String cachedField);
}

class TestCacheClass extends Object implements Proxyble {
    private String cachedField;


    public TestCacheClass(String cachedField) {
        super();
        this.cachedField = cachedField;
    }

    @Cache
    public String getCachedField() {
        return cachedField;
    }

    public void setCachedField(String cachedField) {
        this.cachedField = cachedField;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "TestCacheClass{" +
                "cachedField='" + cachedField + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

public class Main {
    private static void printAllMethods(Class c) {
        Class superClass = c.getSuperclass();
        if(superClass != null) {
            printAllMethods(c.getSuperclass());
        }
        Arrays.asList(c.getDeclaredConstructors()).forEach(constructor -> {
            System.out.println(c.getName()  + " " + constructor.toString());
        });
        Arrays.asList(c.getDeclaredMethods()).forEach(method -> {
            System.out.println(c.getName()  + " " + method.toString());
        });

    }

    private static void printAllGetters(Class c) {
        Arrays.asList(c.getMethods()).forEach(method -> {
            if (MethodUtils.isGetter(method)) {
                System.out.println(c.getName() + " " + method.toString());
            }
        });
    }

    private static boolean isAllNamesEqualValues(Class c) {
        boolean isAllEquals = true;
        for (Field field : Test.class.getFields()) {
            try {
                if (field.getModifiers() == (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL)
                        && field.getType() == String.class
                        && !field.getName().equals(field.get(null))) {
                    isAllEquals = false;
                }
            } catch (IllegalAccessException e) {
                // never throws that
            }
        }

        return isAllEquals;
    }

    public static void main(String[] args) {
        Class analyzedClass = TreeMap.class;

        /** ex.1 */
        System.out.println("===================================================================");
        System.out.println("1. All methods signatures of Class " + analyzedClass.getSimpleName() + ":");
        System.out.println("===================================================================");
        printAllMethods(analyzedClass);

        /** ex.2 */
        System.out.println("===================================================================");
        System.out.println("2. All getters methods signatures of Class " + analyzedClass.getSimpleName() + ":");
        System.out.println("===================================================================");
        printAllGetters(analyzedClass);

        /** ex.3 */
        System.out.println("===================================================================");
        System.out.println("3. Is all public static final string fields of Class " + Test.class.getSimpleName() + " have names equal values:");
        System.out.println("===================================================================");
        if(isAllNamesEqualValues(Test.class)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        /** ex.4 */
        System.out.println("===================================================================");
        System.out.println("4. CacheProxy");
        System.out.println("===================================================================");
        Proxyble noCacheTest = new TestCacheClass("cached");
        System.out.println(noCacheTest.toString());

        Proxyble cacheTest = (Proxyble) CacheProxyUtils.getCacheProxy(noCacheTest);

        System.out.println(noCacheTest.getCachedField());
        System.out.println(cacheTest.getCachedField());

        noCacheTest.setCachedField("no cached");

        System.out.println(noCacheTest.getCachedField());
        System.out.println(cacheTest.getCachedField());

        /** ex.5 */
        System.out.println("===================================================================");
        System.out.println("5. BeanUtils.assign");
        System.out.println("===================================================================");
        TestTo testTo = new TestTo();
        System.out.println(testTo.toString());
        TestFrom testFrom = new TestFrom("Vasya", 5, new Test(), new Test2(), true, 100L);
        System.out.println(testFrom.toString());
        BeanUtils.assign(testTo, testFrom);
        System.out.println(testTo.toString());
    }


}
