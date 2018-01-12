package com.mxnavi.adaptiveicons.opengl.test3;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.mxnavi.adaptiveicons.tool.Printer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class SunnyGLRender implements GLSurfaceView.Renderer {

    private static final String TAG = "SunnyGLRender";

    private int mProgram;
    private int maPostionHandle;

    private FloatBuffer mTriangleVB;
    //定义顶点(vertex)着色器命令语句
    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix; \n" +
                    "attribute vec4 vPosition; \n" +
                    "void main(){              \n" +
                    " gl_Position = vPosition; \n" +
                    "}                         \n";


    //片元着色器
    private final String fragmentShaderCode =
            "precision mediump float;\n" +
                    "void main(){ \n" +
                    " gl_FragColor = vec4 (0.63671875,0.76953125,0.22265625,1.0);\n" +//RGBA
                    "}\n";

    //应用投影与相机视图
    private int muMVPMatrixHandle;
    //用于存储变换矩阵结果的总变换矩阵[4*4]
    private float[] mMVPMatrix = new float[16];
    //[4*4]的视图变换矩阵
    private float[] mVMatrix = new float[16];
    //[4*4]的投影变换矩阵
    private float[] mProjMatrix = new float[16];
    //[4*4]的模型变换矩阵
    private float[] mMMatrix = new float[16];

    public float mAngle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        //GLES20:为OpenGL ES2.0版本,相应的
        //GLES30:OpenGL ES3.0
        //黑色背景
        GLES20.glClearColor(0.5f,0.5f,0.5f,1.0f);
        //glClear:清除缓冲区标志，这里为：清除颜色缓冲及深度缓冲，把整个窗口清除为黑色背景
        GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        initShapes();
        int vertextShader = loadShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);
        //申请特定的着色器，program != 0 申请成功
        mProgram = GLES20.glCreateProgram();
        if(mProgram != 0 ){
            GLES20.glAttachShader(mProgram,vertextShader);
            GLES20.glAttachShader(mProgram,fragmentShader);
            //连接着色器
            GLES20.glLinkProgram(mProgram);
            int[] linkStatus = new int[1];
            //查看着色器连接情况
            GLES20.glGetProgramiv(mProgram, GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] != GLES20.GL_TRUE) {
                //连接失败
                Printer.PRINT(TAG, "Could not link program: ");
                Printer.PRINT(TAG, GLES20.glGetProgramInfoLog(mProgram));
                GLES20.glDeleteProgram(mProgram);
                mProgram = 0;
            }
            //获取着特定着色器vPosition参数。
            maPostionHandle = GLES20.glGetAttribLocation(mProgram,"vPosition");


        }
    }

    /**
     * 初始化三角形的一些参数
     */
    private void initShapes() {
        /**
         * X,Y,Z三轴顶点坐标
         * U,V也即S,T，是图片，视频等以纹理的形式加载到GLSurfaceView中时的坐标，即纹理坐标
         * U,V纹理坐标是无方向的
         */
        //第一种写法：顶点坐标与纹理坐标写一起
        float trianlgeCoords[] = {
                //X,Y,Z，U,V
                -1.0f, -0.5f, 0, 0.0f, 0.5f,
                1.0f, -0.5f, 0, 1.0f, 0.5f,
                0.0f,  1.0f, 0, 0.5f,  0.0f
        };

        //第二种写法：顶点坐标与纹理坐标分开写：(因为Z为0，只考虑二维平面，)
        float veticsCoor[] = {
                //X,Y
                -1.0f,-0.5f,
                1.0f,-0.5f,
                0.0f,1.0f,
        };
        float textureCoor[] = {
                //U,V
                0.0f,0.5f,
                1.0f,0.5f,
                0.5f,0.0f
        };


//把顶点坐标存入Buffer中
        ByteBuffer vbb = ByteBuffer.allocateDirect(trianlgeCoords.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mTriangleVB = vbb.asFloatBuffer();
        mTriangleVB.put(trianlgeCoords);
        mTriangleVB.position(0);
    }

    /**
     * 加载指定着色器
     * @param type
     * @param shaderCode
     * @return
     */
    private int loadShader(int type,String shaderCode){

        int shader = GLES20.glCreateShader(type);
        if(shader != 0 ){
            //加载着色器脚本程序(即本例的String 变量命令语句)
            //我们也可以把顶点着色器与片元着色器以文件的形式加载
            GLES20.glShaderSource(shader,shaderCode);
            //编译着色器脚本程序
            GLES20.glCompileShader(shader);
            //因为GLSL不能像我们写Java或C等，可以debug或Log调试，
            //这里我们就只能通过其返回的值，来判断是否编译通过，
            //如果编译失败，则检查GLSL是否写正确。
            //在写GLSL时，一定要检查参数类型，比如：vec2等2个浮点数的类型是否写成vec3或vec4等了，
            int[] compiled = new int[1];
            //查看编译结果
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {
                //编译失败，释放申请的着色器
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //给定窗口大小
        GLES20.glViewport(0, 0, width, height);

        //
        float ratio = (float) width/height;
        //调用此方法来计算生成透视投影矩阵
        Matrix.frustumM(mProjMatrix,0,-ratio,ratio,-1,1,3,7);
        //当Sucrface改变时，获取指定着色器的uMVPMatrix参数
        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram,"uMVPMatrix");
        //设定相机的视角
        //调用此方法产生摄像机9参数位置矩阵
        Matrix.setLookAtM(mVMatrix,0,
                0,0,-3, //相机的x,y,z坐标
                0,0,0,//目标对应的x,y,z坐标
                0,1.0f,1.0f//相机的视觉向量(upx,upy,upz,三个向量最终的合成向量的方向为相机的方向)
        );

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Redraw background color

        //把左矩阵投影矩阵与右矩阵视图矩阵变换后的结果存储在总矩阵mMVPMatrix中
        Matrix.multiplyMM(mMVPMatrix,0,mProjMatrix,0,mVMatrix,0);

        //为三角形创建一个旋转动作
        /*long time = SystemClock.uptimeMillis() % 4000L;
        mAngle = 0.090f * ((int)time);*/
        //创建一个绕x,y,z轴旋转一定角度的矩阵
        Matrix.setRotateM(mMMatrix,0,mAngle,0,0,1.0f);
        Matrix.multiplyMM(mMVPMatrix,0,mVMatrix,0,mMMatrix,0);
        Matrix.multiplyMM(mMVPMatrix,0,mProjMatrix,0,mMVPMatrix,0);
        GLES20.glUniformMatrix4fv(muMVPMatrixHandle,1,false,mMVPMatrix,0);


        //把Program用到OpenGL环境中
        GLES20.glUseProgram(mProgram);
        //准备画三角形的数据
        //为指定着色器取出的参数赋值   GLES20.glVertexAttribPointer(maPostionHandle,3,GLES20.GL_FLOAT,false,12,mTriangleVB);
        //使用这个从指定着色器中取出的参数的值
        GLES20.glEnableVertexAttribArray(maPostionHandle);

        //开始画
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,3);

    }
}
