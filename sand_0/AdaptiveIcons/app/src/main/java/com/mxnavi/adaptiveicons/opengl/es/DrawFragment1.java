package com.mxnavi.adaptiveicons.opengl.es;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mxnavi.adaptiveicons.R;
import com.mxnavi.adaptiveicons.base.BaseFragment;
import com.mxnavi.adaptiveicons.opengl.MGLSurfaceView;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class DrawFragment1 extends BaseFragment implements View.OnClickListener {

    public static final String TAG = "DrawFragment1";

    private View rootView = null;

    private MGLSurfaceView mGlSurfaceView = null;

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
        if(mGlSurfaceView!=null){
            mGlSurfaceView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mGlSurfaceView!=null){
            mGlSurfaceView.onPause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private View getFragmentView(LayoutInflater inflater, @Nullable ViewGroup container){
        if(rootView == null){
            rootView = inflater.inflate(R.layout.fragment_draw,container,false);
            initWidget();
        }
        return  rootView;
    }

    private void initWidget(){
        mGlSurfaceView = rootView.findViewById(R.id.draw_mglsurface);
        rootView.findViewById(R.id.button_rotate).setOnClickListener(this);
        rootView.findViewById(R.id.button_translate).setOnClickListener(this);
        rootView.findViewById(R.id.button_scale).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_rotate:
                mGlSurfaceView.rotate30();
                break;
            case R.id.button_translate:
                break;
            case R.id.button_scale:
                break;
        }
    }
}
