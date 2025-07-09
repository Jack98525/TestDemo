package org.example.domain;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        // 创建共享的缓冲区
        Buffer buffer = new Buffer(5); // 缓冲区大小为 5

        // 创建生产者和消费者线程
        Thread producer = new Thread(new Producer1(buffer), "Producer");
        Thread consumer = new Thread(new Consumer1(buffer), "Consumer");

        // 启动线程
        producer.start();
        consumer.start();
    }
}

// 缓冲区类
class Buffer {
    private final Queue<Integer> queue; // 用于存储数据的队列
    private final int maxSize;          // 缓冲区的最大容量
    private final Object lock = new Object(); // 锁对象

    public Buffer(int maxSize) {
        this.queue = new LinkedList<>();
        this.maxSize = maxSize;
    }

    // 生产数据
    public void produce(int value) throws InterruptedException {
        synchronized (lock) {
            // 如果缓冲区已满，等待消费者消费
            while (queue.size() == maxSize) {
                System.out.println("Buffer is full. Producer is waiting...");
                lock.wait();
            }

            // 生产数据并加入缓冲区
            queue.add(value);
            System.out.println("Produced: " + value);

            // 通知消费者可以消费
            lock.notifyAll();
        }
    }

    // 消费数据
    public int consume() throws InterruptedException {
        synchronized (lock) {
            // 如果缓冲区为空，等待生产者生产
            while (queue.isEmpty()) {
                System.out.println("Buffer is empty. Consumer is waiting...");
                lock.wait();
            }

            // 消费数据并从缓冲区移除
            int value = queue.poll();
            System.out.println("Consumed: " + value);

            // 通知生产者可以生产
            lock.notifyAll();

            return value;
        }
    }
}

// 生产者类
class Producer1 implements Runnable {
    private final Buffer buffer;

    public Producer1(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            while (true) {
                buffer.produce(value++);
                Thread.sleep(500); // 模拟生产时间
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// 消费者类
class Consumer1 implements Runnable {
    private final Buffer buffer;

    public Consumer1(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consume();
                Thread.sleep(1000); // 模拟消费时间
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}