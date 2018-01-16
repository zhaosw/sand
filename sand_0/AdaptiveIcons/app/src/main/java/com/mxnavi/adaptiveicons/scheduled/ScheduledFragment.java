package com.mxnavi.adaptiveicons.scheduled;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mxnavi.adaptiveicons.R;
import com.mxnavi.adaptiveicons.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class ScheduledFragment extends BaseFragment implements View.OnClickListener ,OnScheduledChangedListener{


    public static final String TAG = "ScheduledFragment";

    public static final String SCHEDULED_HANDLER_THREAD = "scheduled_handler_thread";

    private EditText mEditText = null;

    private View rootView = null;

    private MHandlerThread mHandlerThread = null;

    private Handler threadHandler = null;

    private int count = 0;

    private Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 10:
                    mEditText.setText("handle message:"+msg.arg1);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getFragmentView(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private View getFragmentView(LayoutInflater inflater, @Nullable ViewGroup container){
        if(rootView == null){
            rootView = inflater.inflate(R.layout.fragment_scheduled,container,false);
            initWidget();
        }
        return  rootView;
    }

    private void initWidget(){
        mHandlerThread = new MHandlerThread(SCHEDULED_HANDLER_THREAD);
        mHandlerThread.setOnScheduledChangedListener(this);
        mHandlerThread.start();
        threadHandler = new Handler(mHandlerThread.getLooper(),mHandlerThread);

        mEditText = rootView.findViewById(R.id.editText);
        rootView.findViewById(R.id.button4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button4:
                count ++;
                sendThreadMessage(10,count);
                break;
        }
    }

    private void sendThreadMessage(int what,int arg1){
        Message message = Message.obtain();
        message.what = what;
        message.arg1 = arg1;
        threadHandler.sendMessage(message);
    }

    @Override
    public void onScheduledChanged(Message message) {
        Message msg = Message.obtain();
        msg.copyFrom(message);
        switch (message.what){
            case 10:
                mainHandler.sendMessage(msg);
                break;
        }
    }
}
