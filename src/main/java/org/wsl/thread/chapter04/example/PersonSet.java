package org.wsl.thread.chapter04.example;

import org.wsl.thread.chapter04.pojo.Person;

import java.util.HashSet;
import java.util.Set;

/**
 * 类封闭example
 */
public class PersonSet {

    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person person){
        mySet.add(person);
    }

    public synchronized boolean containsPerson(Person person) {
        return mySet.contains(person);
    }
}
