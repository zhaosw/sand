package com.mxnavi.adaptiveicons.scheduled;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

@SuppressLint("LongLogTag")
public class Scheduled {

    private static final String TAG = "ScheduledExecutorService";

    private ScheduledExecutorService scheduledExecutorService = null;

    private static Scheduled mScheduled = null;

    public static Scheduled getInstance(){
        if(mScheduled == null){
            mScheduled = new Scheduled();
        }
        return mScheduled;
    }

    public  ScheduledExecutorService getScheduledExecutorService(){
        if(scheduledExecutorService == null||scheduledExecutorService.isShutdown()||scheduledExecutorService.isTerminated()){
            scheduledExecutorService = Executors.newScheduledThreadPool(3);
        }
        return scheduledExecutorService;
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  TimeUnit unit){
        return getScheduledExecutorService().scheduleAtFixedRate(command,initialDelay,period, unit);
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
                                                     long initialDelay,
                                                     long delay,
                                                     TimeUnit unit){
        return getScheduledExecutorService().scheduleWithFixedDelay(command,initialDelay,delay, unit);
    }


    public void cancle(){
        getScheduledExecutorService().shutdownNow();
    }



    private void scheduled(){
        getScheduledExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"thread runnable");
            }
        });
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG,"timerTask running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        getScheduledExecutorService().scheduleAtFixedRate(timerTask,1000,1000, TimeUnit.MILLISECONDS);
        getScheduledExecutorService().scheduleWithFixedDelay(timerTask,1000,1000, TimeUnit.MILLISECONDS);
    }

}
