package com.mxnavi.adaptiveicons.scheduled;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.mxnavi.adaptiveicons.tool.Printer;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class MHandlerThread extends HandlerThread implements Handler.Callback {

    private OnScheduledChangedListener mOnScheduledChangedListener = null;

    public MHandlerThread(String name) {
        super(name);
    }

    public void setOnScheduledChangedListener(OnScheduledChangedListener onScheduledChangedListener){
        this.mOnScheduledChangedListener = onScheduledChangedListener;
    }

    @Override
    public void run() {
        super.run();
        Printer.PRINT(ScheduledFragment.TAG,"MHandlerThread run()");
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        Printer.PRINT(ScheduledFragment.TAG,"MHandlerThread onLooperPrepared()");
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg != null){
            Printer.PRINT(ScheduledFragment.TAG,"MHandlerThread handleMessage "+msg.what);
            if(mOnScheduledChangedListener!=null){
                mOnScheduledChangedListener.onScheduledChanged(msg);
            }
        }
        return false;
    }
}
