package jaba_12_multi_thread.blocking_task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExistReturnTask {

    private static Callable<Integer> getTask() {
        return () -> {
            int sum = 0;
            for(int i = 1; i <= 10; i++) {sum += i;}
            return sum;
        };
    }

    public static void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        System.out.println("[작업 처리 요청]");

        Callable<Integer> callableTask = getTask();

        Future<Integer> future = executorService.submit(callableTask);

        try {
            int sum = future.get(); // 처리 결과 반환
            System.out.println("[작업 처리 완료]" + sum);
        } catch (Exception e) {}

        executorService.shutdown();
    }
}
