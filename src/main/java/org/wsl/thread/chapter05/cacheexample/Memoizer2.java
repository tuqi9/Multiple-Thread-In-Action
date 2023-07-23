package org.wsl.thread.chapter05.cacheexample;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * cache v2
 * 优化思路: 上锁的目的是控制对存有缓存的map的并发访问,v1中使用synchronize对
 * 访问容器加锁。v2版本采用线程安全的集合,基于此来减小上锁范围
 */
@SuppressWarnings("all")
public class Memoizer2<A,V> implements Computable<A, V>{

    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * v2存在的问题:
     * 对于结果容器cache的并发访问已经能够控制,但对于方法:Memoizer2.compute()而言,
     * 它是一种先检查再执行的模式,并且这个检查再执行不是线程安全
     * 因此可能存在两个线程进入了这个方法,并且都判断不存在,转而执行一次昂贵计算,造成资源的浪费
     *
     * 优化思路:
     * 1. 对于先检查再执行的行为加锁,这类似于v1版本会导致效率底下
     * 2. 对正在执行昂贵计算的任务封装,将其装入cache,基于该任务的封装来告诉后来线程,该项计算已经有线程在执行了
     *
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        V value = cache.get(arg);
        if (value == null) {
            value = c.compute(arg);
            cache.put(arg, value);
        }
        return value;
    }
}
