package org.example.domain;

public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            threadLocal.set(1);
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        });

        Thread thread2 = new Thread(() -> {
            threadLocal.set(2);
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        });

        thread1.start();
        thread2.start();
    }
}
