1. 迭代器与ConcurrentModificationException
   1. 很多容器类没有消除符合操作中的并发问题。它们在设计同步容器的迭代器时也未考虑并发修改的问题，而是表现出及时失败(fail-fast).
   2. 