package com.mxnavi.adaptiveicons.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.mxnavi.adaptiveicons.opengl.test1.GLRender1;
import com.mxnavi.adaptiveicons.opengl.test2.MGLRender2;
import com.mxnavi.adaptiveicons.opengl.test3.SunnyGLRender;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class MGLSurfaceView extends GLSurfaceView {

    private MGLRender2 mGlRender = null;

//    private GLRender1 mGLRender1 = null;

//    private MGLRender mGlRender = null;

//    private SunnyGLRender mSunnyGLRender = null;

    public MGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGlRender = new MGLRender2();
//        mGlRender = new GLRender1(context);
//        mSunnyGLRender = new SunnyGLRender();
        setRenderer(mGlRender);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void rotate30(){
//        mGlRender.rotate30();
    }

}
