package org.wsl.thread.chapter05.cacheexample;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @param <A>
 * @param <V>
 */
@SuppressWarnings("all")
public class Memoizer1<A, V> implements Computable<A, V> {

    private final Map<A,V> cache = new HashMap<A,V>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * cache v1
     * 通过 synchronize 锁住,因此尽管HashMap并非线程安全,依然能够通过这样的方法保证线程安全
     *
     * 优点: 简单，容易实现
     * 缺点: 上锁范围太大,导致并发情况下效率非常低
     * 优化: cache v2
     */
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V value = cache.get(arg);
        if (value == null) {
            value = c.compute(arg);
            cache.put(arg, value);
        }
        return value;
    }
}
