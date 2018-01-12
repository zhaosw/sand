package com.mxnavi.adaptiveicons.opengl.test2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class PumpKinDot {

    private FloatBuffer vertexsBuffer;

    private FloatBuffer colorsBuffer;

    //x,y,z
    private float[] vertexs=new float[]{
            1.0f,0.0f,0.0f,
            0.0f,1.0f,0.0f,
            -1.0f,0.0f,0.0f,
            0.0f,-1.0f,0.0f,
            0.5f,0.0f,0.0f,
            0.0f,0.5f,0.0f,
            -0.5f,0.0f,0.0f,
            0.0f,-0.5f,0.0f,
            0.0f,0.0f,0.0f,
            0.0f,0.0f,1.0f,
            0.0f,0.0f,-1.0f,
            0.0f,0.0f,0.5f,
            0.0f,0.0f,-0.5f,
            0.0f,0.0f,0.0f

    };

    //r,g,b,a
    private float[] colors={
            0.0f,1.0f,0.0f,1.0f,
            1.0f,0.0f,0.0f,1.0f,
            0.0f,1.0f,0.0f,1.0f,
            1.0f,0.0f,0.0f,1.0f,
            0.0f,1.0f,0.0f,1.0f,
            1.0f,1.0f,0.0f,1.0f,
            0.0f,1.0f,0.0f,1.0f,
            1.0f,0.0f,0.0f,1.0f,
            1.0f,1.0f,1.0f,1.0f,
            1.0f,1.0f,1.0f,1.0f,
            1.0f,1.0f,1.0f,1.0f,
            1.0f,1.0f,1.0f,1.0f,
            1.0f,1.0f,1.0f,1.0f,
            1.0f,1.0f,1.0f,1.0f,
    };

    public PumpKinDot(){

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

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertexsBuffer);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4,GL10.GL_FLOAT,0,colorsBuffer);
        gl.glDrawArrays(GL10.GL_POINTS,0,vertexs.length/3);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    }

}