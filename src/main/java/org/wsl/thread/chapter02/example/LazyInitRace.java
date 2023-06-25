package org.wsl.thread.chapter02.example;

/**
 * 单例模式下的延迟初始化,是一种典型的 Check-Then-Act
 */
public class LazyInitRace {

    /** expensive Object */
    private Object instance = null;

    public Object getInstance(){
        if (instance == null) {
            instance = new Object();
        }
        return instance;
    }
}
