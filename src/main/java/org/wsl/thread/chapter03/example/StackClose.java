package org.wsl.thread.chapter03.example;

import org.wsl.thread.chapter03.pojo.Animal;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

public class StackClose {

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numberPairs = 0;
        Animal candidate = null;

        // animals 被封闭在栈中
        animals = new TreeSet<>();
        animals.addAll(candidates);

        for (Animal a : animals) {
            if (candidate == null) {
                // doSomething()
            } else {
                // doSomething()
            }
        }
        return numberPairs;
    }
}
