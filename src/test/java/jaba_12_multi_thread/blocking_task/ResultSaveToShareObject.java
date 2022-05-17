package jaba_12_multi_thread.blocking_task;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ResultSaveToShareObject {

    private Runnable getTask(SharedValue sharedValue) {
        return () -> {
            int sum = 0;
            for(int i = 1; i <= 10; i++) { sum += i; }
            sharedValue.addValue(sum);
        };
    }

    @Test
    public void run() {
        SharedValue sharedValue = new SharedValue();
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        Runnable task1 = getTask(sharedValue);
        Runnable task2 = getTask(sharedValue);
        Future<SharedValue> future1 = executorService.submit(task1, sharedValue);
        Future<SharedValue> future2 = executorService.submit(task2, sharedValue);

        try {
            // get 은 작업 요청일 뿐, 작업 처리 그 자체는 아니다. 작업이 순차적으로 이뤄질지는 보장할 수 없음
            sharedValue = future1.get();
            sharedValue = future2.get();
            System.out.println("처리 결과: " + sharedValue.accumValue);
            System.out.println("처리 완료");
        }catch (Exception e) {}
    }

    class SharedValue {
        int accumValue;
        synchronized void addValue(int value) {
            accumValue += value;
        }
    }
}
