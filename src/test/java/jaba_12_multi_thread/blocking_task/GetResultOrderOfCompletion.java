package jaba_12_multi_thread.blocking_task;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class GetResultOrderOfCompletion {

    private Callable<Integer> getTask() {
        return () -> {
            int sum = 0;
            for(int i = 1; i <= 10; i++) { sum += i; }
            return sum;
        };
    }

    private Runnable takeTaskIfComplete(CompletionService<Integer> completionService) {
        return () -> {
            while (true) {
                try {
                    Future<Integer> future = completionService.take();
                    int value = future.get();
                    System.out.println("처리 결과: " + value);
                } catch (InterruptedException e) { // shutdownNow 호출시 take로 가져올게 없다면 해당 예외 발생
                    break;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    break;
                }
            }
        };
    }

    @Test
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);

        for(int i = 0; i < 3; i++) {
            completionService.submit(getTask());
        }

        executorService.submit(takeTaskIfComplete(completionService));

        try {Thread.sleep(3000); } catch (InterruptedException e) {}  // 컴파일 타임에 3초안에 작업이 완료될꺼라 가정하고 작성. 만일 런타임에 작업 완료 후 종료시킬려면?
        executorService.shutdownNow();

    }
}
