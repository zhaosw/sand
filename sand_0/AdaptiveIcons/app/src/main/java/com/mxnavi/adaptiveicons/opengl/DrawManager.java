package com.mxnavi.adaptiveicons.opengl;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;

import com.mxnavi.adaptiveicons.tool.Printer;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class DrawManager {

    private static final String TAG = "DrawManager";

    private static DrawManager mDrawManager = null;

    public static DrawManager getInstance(){
        if(mDrawManager == null){
            mDrawManager = new DrawManager();
        }
        return mDrawManager;
    }

    public boolean isSurported(Context context){
        final ActivityManager activityManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo=activityManager.getDeviceConfigurationInfo();
        boolean supportsEs2=configurationInfo.reqGlEsVersion>=0x2000;
        Printer.PRINT(TAG,"isSurported opengl:"+supportsEs2+"; "+configurationInfo.reqGlEsVersion);
        return supportsEs2;
    }



}
