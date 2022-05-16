package jaba_12_multi_thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class ThreadPoolExample {
    public static void main(String[] args)  {
        ThreadPoolExample.executeExample();
    }

    static private void executeExample() {
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
