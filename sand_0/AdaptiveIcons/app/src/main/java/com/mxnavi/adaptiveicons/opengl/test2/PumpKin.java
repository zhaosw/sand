package com.mxnavi.adaptiveicons.opengl.test2;

import com.mxnavi.adaptiveicons.scheduled.Scheduled;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class PumpKin {

    private FloatBuffer vertexsBuffer;
    private FloatBuffer colorsBuffer;

    private int angle = 0;
    private float x = 0;
    private float y = 0;
    private float z = 0;
    boolean translate_p = true;

    private TimerTask mRotateTask = null;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }


    private ByteBuffer indicesBuffer;

    /*private float vertexs[]={
            -3.0f,0.0f,0.0f,
            0.0f,1.0f,0.0f,
            3.0f,0.0f,0.0f,
            0.0f,-3.0f,0.0f,
            1.0f,0.0f,0.0f,
            0.0f,3.0f,0.0f,
            0.0f,0.0f,-3.0f,
            1.0f,0.0f,0.0f,
            0.0f,0.0f,3.0f
    };*/

    private float vertexs[]={
            0.0f,0.5f,0.0f,
            -0.5f,0.0f,0.0f,
            0.5f,0.0f,0.0f,
            0.0f,0.5f,0.0f,
            -0.5f,0.0f,0.0f,
            0.0f,0.0f,0.5f,
            0.0f,0.5f,0.0f,
            0.0f,0.0f,0.5f,
            0.5f,0.0f,0.0f,
    };
    private float colors[]={
            1.0f,0.0f,0.0f,1.0f,
            1.0f,0.0f,0.0f,1.0f,
            1.0f,0.0f,0.0f,1.0f,
            0.0f,1.0f,0.0f,1.0f,
            0.0f,1.0f,0.0f,1.0f,
            0.0f,1.0f,0.0f,1.0f,
            0.0f,0.0f,1.0f,1.0f,
            0.0f,0.0f,1.0f,1.0f,
            0.0f,0.0f,1.0f,1.0f
    };


    /*private float colors[]={
            1.0f,0.0f,0.0f,1.0f,
            1.0f,0.0f,0.0f,1.0f,
            1.0f,0.0f,0.0f,1.0f,
            0.0f,1.0f,0.0f,1.0f,
            0.0f,1.0f,0.0f,1.0f,
            0.0f,1.0f,0.0f,1.0f,
            0.0f,0.0f,1.0f,1.0f,
            0.0f,0.0f,1.0f,1.0f,
            0.0f,0.0f,1.0f,1.0f
    };*/

    private byte indices[]={0,1,2};

    public PumpKin(){

        ByteBuffer vbb=ByteBuffer.allocateDirect(vertexs.length*4);
        vbb.order(ByteOrder.nativeOrder());
        vertexsBuffer=vbb.asFloatBuffer();
        vertexsBuffer.put(vertexs);
        vertexsBuffer.position(0);

        ByteBuffer cbb=ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());
        colorsBuffer=cbb.asFloatBuffer();
        colorsBuffer.put(colors);
        colorsBuffer.position(0);

    }

    public void draw(GL10 gl){
        gl.glTranslatef(x, y, z);
        gl.glRotatef(getAngle(),0.0f,1.0f,0.0f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertexsBuffer);
        gl.glColorPointer(4,GL10.GL_FLOAT,0,colorsBuffer);

        gl.glDrawArrays(GL10.GL_TRIANGLES,0,vertexs.length/3);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    }

    public void startRotate(){
        if(mRotateTask != null){
            return;
        }
        mRotateTask = new TimerTask() {
            @Override
            public void run() {
                angle += 10;
                if(angle> Parameter.ANGLE_MAX){
                    angle = 10;
                }
                if(translate_p){
                    x+=0.1;
                    y+=0.1;
                    z+=0.1;
                }else{
                    x-=0.1;
                    y-=0.1;
                    z-=0.1;
                }
                if(x>0.5){
                    translate_p = false;
                }else if(x<-0.5){
                    translate_p = true;
                }
            }
        };
        Scheduled.getInstance().scheduleAtFixedRate(mRotateTask,100,100, TimeUnit.MILLISECONDS);
    }

}
