package org.wsl.thread.chapter03.example;

public class UnsafeStates {

    private String[] states = new String[]{"AK","AL","AKL"};

    /** 私有属性 states 被发布 */
    public String[] getStates() { return states; }
}
