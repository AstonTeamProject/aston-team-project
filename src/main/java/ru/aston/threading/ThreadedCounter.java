package ru.aston.threading;

import ru.aston.entity.Student;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadedCounter {
    public int countOccurrences(List<Student> list, Student target) {
        if (list == null || list.isEmpty()) {
            System.out.println("Collection is empty");
            return 0;
        }

        int threadCount = Math.min(list.size(), Runtime.getRuntime().availableProcessors());
        AtomicInteger totalCount = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        try {
            int chunkSize = (list.size() + threadCount - 1) / threadCount;

            for (int i = 0; i < threadCount; i++) {
                final int start = i * chunkSize;
                final int end = Math.min(start + chunkSize, list.size());

                executor.submit(() -> {
                    int localCount = 0;
                    for (int j = start; j < end; j++) {
                        if (list.get(j).equals(target)) {
                            localCount++;
                        }
                    }
                    totalCount.addAndGet(localCount);
                });
            }

            executor.shutdown();
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
                System.out.println("Timeout waiting for tasks to complete");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("Calculation interrupted: " + e.getMessage());
            return 0;
        }

        return totalCount.get();
    }
}