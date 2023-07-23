package org.wsl.thread.chapter05.cacheexample;

/**
 * 申明一种计算操作
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
