package com.mxnavi.adaptiveicons.tool;

import android.util.Log;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class Printer {

    private static final boolean Debug = true;

    private static final String Application_TAG = "AdaptiveIcons:";

    public static void PRINT(String tag,String message){
        if(Debug){
            Log.d(Application_TAG+tag,message);
        }
    }

}
