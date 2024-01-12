package com.effect.playground.ioruntime;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FuturesInParallel {
    public static void main(String[] args) {
       var executor = Executors.newCachedThreadPool();
       var start = java.time.Instant.now();
        var completableFutures = new java.util.ArrayList<CompletableFuture>();

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            completableFutures.add(CompletableFuture.runAsync(() -> {
                try {
                    System.out.println("Running task %s with thread %s".formatted(String.valueOf(finalI), Thread.currentThread().getName()));
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, executor));
        }



        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).join();


        var end = java.time.Instant.now();
        var between = java.time.Duration.between(start, end);

        System.out.println("The function took %s secs.".formatted(between.toSeconds()));
        System.exit(0);
    }

}


