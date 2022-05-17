package jaba_12_multi_thread.run_task_method;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * RunTaskWithExecute 의 예외시 생성 오버헤드를 줄인 버전.
 * 예외가 발생하더라도 생성된 스레드를 그대로 사용함
 * */
public class RunTaskWithSubmit {
    @Test
    void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 10; i ++) {
            executorService.submit(() -> {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                int poolSize = threadPoolExecutor.getPoolSize();
                String threadName = Thread.currentThread().getName();
                System.out.println("총 스레드 개수:" + poolSize + " 작업 스레드 이름:" + threadName);

                // 강제 예외 발생
                Integer.parseInt("참");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        executorService.shutdown();
    }
}
