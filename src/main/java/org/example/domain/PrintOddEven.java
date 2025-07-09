package org.example.domain;

public class PrintOddEven {

    private static final Object lock = new Object();
    private static int count = 1;
    public static final int MAX_COUNT = 100;

    public static void main(String[] args) {
        Runnable printEven = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (count <= MAX_COUNT) {
                        if (count % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + ": " + count++);
                            lock.notify();
                        } else {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }

                }

            }
        };

        Runnable printOdd = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (count <= MAX_COUNT) {
                        if (count % 2 != 0) {
                            System.out.println(Thread.currentThread().getName() + ": " + count++);
                            lock.notify();
                        } else {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }

                }

            }
        };

        new Thread(printOdd,"OddThread").start();
        new Thread(printEven,"EvenThread").start();


    }


}
