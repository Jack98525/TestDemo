package package2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时任务
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, CompletableFuture!";
        });

        // 非阻塞获取结果
        future.thenAccept(result -> System.out.println("Result: " + result));

        System.out.println("Main thread is not blocked.");
        Thread.sleep(1200);

    }
}
