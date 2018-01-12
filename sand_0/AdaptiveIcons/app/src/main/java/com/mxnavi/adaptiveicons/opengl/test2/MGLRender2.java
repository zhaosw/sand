package com.mxnavi.adaptiveicons.opengl.test2;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.mxnavi.adaptiveicons.tool.Printer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by Administrator on 2017/12/27 0027.
 */

/**
 * Package com.hc.opengl
 * Created by HuaChao on 2016/7/28.
 */
public class MGLRender2 implements GLSurfaceView.Renderer {

//    private PumpKinDot pumpKinDot;
    private PumpKin pumpKin;

    public MGLRender2() {

//        pumpKinDot = new PumpKinDot();
        pumpKin=new PumpKin();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        pumpKin.startRotate();
        if (height == 0) {
            height = 1;
        }
        float aspect = (float) width / height;

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_PROJECTION);//投影
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.0f);
//        gl.glMatrixMode(GL10.GL_MODELVIEW);
//        gl.glLoadIdentity();

    }

    @Override
    public void onDrawFrame(GL10 gl) {

//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT );
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        pumpKin.draw(gl);
//        pumpKinDot.draw(gl);


    }

    private FloatBuffer getFloatBuffer(float[] floatArray){
        ByteBuffer bb = ByteBuffer.allocateDirect(floatArray.length * 4);
        //以本机字节顺序来修改此缓冲区的字节顺序
        bb.order(ByteOrder.nativeOrder());
        return (FloatBuffer) bb.asFloatBuffer().put(floatArray).position(0);
    }


}
