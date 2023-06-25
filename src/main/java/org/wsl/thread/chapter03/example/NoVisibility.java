package org.wsl.thread.chapter03.example;

import javax.annotation.concurrent.GuardedBy;

public class NoVisibility {

    private static boolean ready;

    private static int number;

    /**
     * 写线程更新的值“可能”不会被读线程获取到
     * 从而导致读限制一直循环下去
     */
    public static void main(String[] args) {
        System.out.println("---> " + ready);
        System.out.println("---> " + number);
        new ReaderThread().start();
        number = 42;
        ready = true;
    }


    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

}
