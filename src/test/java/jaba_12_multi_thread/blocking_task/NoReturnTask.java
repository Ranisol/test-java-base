package jaba_12_multi_thread.blocking_task;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NoReturnTask {

    private static Runnable getTask() {
        return () -> {
            int sum = 0;
            for(int i = 1; i <= 10; i++) {sum += i;}
            System.out.println("처리 결과: " + sum);
        };
    }

    @Test
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        System.out.println("[작업 처리 요청]");

        Runnable runnableTask = getTask();

        Future future = executorService.submit(runnableTask);

        try {
            future.get(); // 처리 결과 없음
            System.out.println("[작업 처리 완료]");
        } catch (Exception e) {}

        executorService.shutdown();
    }

}
