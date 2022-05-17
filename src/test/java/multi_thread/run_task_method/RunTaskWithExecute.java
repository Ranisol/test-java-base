package multi_thread.run_task_method;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 스레드가 강제종료되면서 스레드도 종료되고, 다음애는 새로운 스레드 사용함
 * */
public class RunTaskWithExecute {
    @Test
    void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 10; i ++) {
            executorService.execute(() -> {
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
