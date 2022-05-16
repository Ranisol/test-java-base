package jaba_12_multi_thread.callback_task;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackTask {

    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        executorService.submit(getTask("3", "3"));
        executorService.submit(getTask("3", "삼"));
        executorService.shutdown();
    }

    private CompletionHandler<Integer, Void> callback = new CompletionHandler<>() {
        @Override
        public void completed(Integer result, Void attachment) {
            System.out.println("complete 실행:" + result);
        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            System.out.println("fail 실행" + exc.toString() );
        }
    };

    private Runnable getTask(final String x, final String y) {
        return () -> {
            try {
                int intX = Integer.parseInt(x);
                int intY = Integer.parseInt(y);
                int result = intX + intY;
                callback.completed(result, null);
            } catch (NumberFormatException e) {
                callback.failed(e, null);
            }
        };
    }

}
