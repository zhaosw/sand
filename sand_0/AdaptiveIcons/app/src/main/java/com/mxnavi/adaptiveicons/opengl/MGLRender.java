package com.mxnavi.adaptiveicons.opengl;

import android.opengl.GLSurfaceView;

import com.mxnavi.adaptiveicons.tool.Printer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static javax.microedition.khronos.opengles.GL10.*;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

/**
 * Package com.hc.opengl
 * Created by HuaChao on 2016/7/28.
 */
public class MGLRender implements GLSurfaceView.Renderer {

    private static final String TAG = "MGLRender";

    private int x_translate = 1;

    private int y_translate = 1;

    private int z_translate = 1;

    private int x_t_current = 0;

    private float[] mTriangleArray = {
            0f, 1f, 0f,
            -1f, -1f, 0f,
            1f, -1f, 0f,
    };

    private float[] mTriangleArray1 = {
            1f, 3f, 0f,
            0f, 1f, 0f,
            2f, 1f, 0f,
    };

    //三角形各顶点颜色(三个顶点)
    private float[] mColor = new float[]{
            1, 1, 0, 1,
            0, 1, 1, 1,
            1, 0, 1, 1,
    };
    private FloatBuffer mTriangleBuffer;
    private FloatBuffer mTriangleBuffer1 = null;

    private FloatBuffer mColorBuffer;


    public MGLRender() {

        Printer.PRINT(TAG,"constructor");

        //点相关
        //先初始化buffer，数组的长度*4，因为一个float占4个字节
        ByteBuffer bb = ByteBuffer.allocateDirect(mTriangleArray.length * 4);
        //以本机字节顺序来修改此缓冲区的字节顺序
        bb.order(ByteOrder.nativeOrder());
        mTriangleBuffer = bb.asFloatBuffer();
        //将给定float[]数据从当前位置开始，依次写入此缓冲区
        mTriangleBuffer.put(mTriangleArray);
        //设置此缓冲区的位置。如果标记已定义并且大于新的位置，则要丢弃该标记。
        mTriangleBuffer.position(0);

        mTriangleBuffer1 = getFloatBuffer(mTriangleArray1);

        //颜色相关
        ByteBuffer bb2 = ByteBuffer.allocateDirect(mColor.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        mColorBuffer = bb2.asFloatBuffer();
        mColorBuffer.put(mColor);
        mColorBuffer.position(0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Printer.PRINT(TAG,"onDrawFrame");

        // 清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // 重置当前的模型观察矩阵
        gl.glLoadIdentity();

        // 允许设置顶点
        //GL10.GL_VERTEX_ARRAY顶点数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // 允许设置颜色
        //GL10.GL_COLOR_ARRAY颜色数组
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        //将三角形在z轴上移动
        gl.glTranslatef(0, 0.0f, -2.0f);
//        gl.glTranslatef(x_translate, 0.0f, 0.0f);
//        x_t_current+=x_translate;
//        if(x_t_current>100){
//            x_translate = -1;
//        }else if(x_t_current<-100){
//            x_translate = 1;
//        }

//        gl.glRotatef(30, 1, 0, 0);

        //x，y，z方向放缩0.1倍
//        gl.glScalef(0.1f, 0.1f, 0.1f);

        // 设置三角形
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);

        // 设置三角形1
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer1);
        // 设置三角形颜色
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
        // 绘制三角形
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 4);

        // 取消颜色设置
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        // 取消顶点设置
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        //绘制结束
        gl.glFinish();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Printer.PRINT(TAG,"onSurfaceChanged");
        float ratio = (float) width / height;
        // 设置OpenGL场景的大小,(0,0)表示窗口内部视口的左下角，(w,h)指定了视口的大小
        gl.glViewport(0, 0, width, height);
        // 设置投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // 重置投影矩阵
        gl.glLoadIdentity();
        // 设置视口的大小
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
        //以下两句声明，以后所有的变换都是针对模型(即我们绘制的图形)
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Printer.PRINT(TAG,"onSurfaceCreated");
        // 设置白色为清屏
        gl.glClearColor(1, 1, 1, 1);

    }

    private FloatBuffer getFloatBuffer(float[] floatArray){
        ByteBuffer bb = ByteBuffer.allocateDirect(floatArray.length * 4);
        //以本机字节顺序来修改此缓冲区的字节顺序
        bb.order(ByteOrder.nativeOrder());
        return (FloatBuffer) bb.asFloatBuffer().put(floatArray).position(0);
    }

    public void rotate30(){

    }

}
