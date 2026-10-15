package io.github.spylent1.flashpass.utils;

public interface ILock {
    boolean tryLock(long timeoutSec);
    void unlock();
}
