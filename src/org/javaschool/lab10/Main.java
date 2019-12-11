package org.javaschool.lab10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

class Person extends Object{
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person() {
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
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

public class Main {
    public static void main(String[] args) {
        List<Person> someCollection = new ArrayList<>();
        someCollection.add(new Person("Vasya", 25));
        someCollection.add(new Person("Petya", 15));
        someCollection.add(new Person("Kolya", 30));
        someCollection.add(new Person("Vanya", 45));


        //someCollection.stream().filter(p -> p.getAge() > 20);
        //ListStream personStream = ListStream.<Person>of(someCollection).filter();
        Map<String, Person> m =ListStream.<Person>of(someCollection)
                .filter(p -> ((Person) p).getAge() > 20)
                .transform(p -> new Person(((Person) p).getName(), ((Person) p).getAge() + 30))
                .toMap(p -> ((Person) p).getName() , p -> p, new HashMap<String, Person>());

        m.forEach((k,v) ->System.out.println(k + " : " + v));
    }
}
