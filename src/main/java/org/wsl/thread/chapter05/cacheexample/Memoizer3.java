package org.wsl.thread.chapter05.cacheexample;

import java.util.Map;
import java.util.concurrent.*;

@SuppressWarnings("all")
public class Memoizer3<A,V> implements Computable<A, V>{

    private final Map<A, Future<V>> cache
            = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * 实际上v3版本已经很好的解决了并发效率的问题
     * 问题: 没有解决缓存污染的问题
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> value = cache.get(arg);
            if (value == null) {
                Callable<V> eval = () -> c.compute(arg);
                FutureTask<V> futureTask = new FutureTask<>(eval);
                value = cache.putIfAbsent(arg, futureTask);
                if (value == null) {
                    value = futureTask;
                    futureTask.run();
                }
            }

            try {
                return value.get();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
