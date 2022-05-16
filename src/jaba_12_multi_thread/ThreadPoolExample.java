package jaba_12_multi_thread;


import jaba_12_multi_thread.blocking_task.GetResultOrderOfCompletion;
import jaba_12_multi_thread.callback_task.CallbackTask;

class ThreadPoolExample {
    public static void main(String[] args)  {
        // RunTaskWithExecute.run();
        // RunTaskWithSubmit.run();
        // NoReturnTask.run();
        // ExistReturnTask.run();
        // new SaveResultToShareObject().run();
        // new GetResultOrderOfCompletion().run();
        new CallbackTask().run();
    }

}
