package org.wsl.thread.chapter02.example;

public class ReentrySynchronized {

    public class Widget{
        public synchronized void doSomething() {

        }
    }

    public class LoggingWidget extends Widget {
        @Override
        public synchronized void doSomething() {
            System.out.println(toString() + ": calling doSomething");
            // 如果锁不是可重入的,该方法调用会造成死锁
            super.doSomething();
        }
    }
}
