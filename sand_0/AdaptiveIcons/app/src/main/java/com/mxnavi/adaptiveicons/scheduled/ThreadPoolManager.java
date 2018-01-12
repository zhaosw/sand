package com.mxnavi.adaptiveicons.scheduled;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public class ThreadPoolManager {

    private static ThreadPoolManager mThreadPoolManager = null;

    private static ExecutorService executorService = null;

    public ThreadPoolManager(){

    }

    public static ThreadPoolManager getInstance(){
        if(mThreadPoolManager == null){
            mThreadPoolManager = new ThreadPoolManager();
        }
        if(executorService == null||executorService.isShutdown()||executorService.isTerminated()){
            executorService = Executors.newFixedThreadPool(10);
        }
        return mThreadPoolManager;
    }

    public void addTask(Runnable runnable){
        executorService.submit(runnable);
    }

    public void shutDown(){
        executorService.shutdown();
    }

    public void shutDownNow(){
        executorService.shutdownNow();
    }

}
