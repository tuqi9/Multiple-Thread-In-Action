1. 无状态的对象一定是线程安全的，比如大多是情况下不保存任何状态的Servlet程序
2. 比如需要对一个无状态的Servlet增加一个标记为，用于记录请求的次数。使用private long count;就不是一个很好的选择,而应该使用private AtomicLong count;
   1. AtomicLong 提供了原子的 ++count操作，是一个线程安全对象。
3. 竞态条件：
   1. 最常见的竞态条件：先检查后执行(Check-Then-Act).示例:LazyInitRace.java
4. 内置锁
   1. Java内置锁synchronized是一种独占锁、互斥锁。线程在进入同步代码块时会自动获取锁，退出代码块时自动释放锁。
5. 重入:A线程获取一个它已经持有的锁，如果可以的话，则这个锁就是重入锁。***内置锁是重入锁***。
   1. “重入”意味着获取锁的粒度是“线程”，而不是“调用”。示例:ReentrySynchronized.java