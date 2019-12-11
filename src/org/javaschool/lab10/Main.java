package org.javaschool.lab10;

import java.util.ArrayList;
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
}

public class Main {
    public static void main(String[] args) {
        List<Person> someCollection = new ArrayList<>();

        Map<String, Person> m = ListStream.<Person>of(someCollection)
                .filter((Person p) -> p.getAge() > 20)
                .transform((Person p) -> new Person(p.getName(), p.geAge() + 30))
                .toMap((Person p) -> p.geName(), (Person p) -> p});

    }
}
