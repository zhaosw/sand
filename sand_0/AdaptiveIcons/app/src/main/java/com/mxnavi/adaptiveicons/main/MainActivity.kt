package com.mxnavi.adaptiveicons

import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
import com.mxnavi.adaptiveicons.base.BaseActivity
import com.mxnavi.adaptiveicons.main.MainFragment
import com.mxnavi.adaptiveicons.opengl.DrawManager
import com.mxnavi.adaptiveicons.scheduled.Scheduled
import com.mxnavi.adaptiveicons.scheduled.ThreadPoolManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var mMainFragment: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMainFragment(savedInstanceState)
        DrawManager.getInstance().isSurported(this)
    }

    fun initMainFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            mMainFragment = MainFragment()
            supportFragmentManager.beginTransaction().add(R.id.main_frame,mMainFragment,MainFragment.TAG).commit()
        }else{
            mMainFragment = supportFragmentManager.findFragmentByTag(MainFragment.TAG) as MainFragment?
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun stringBtnFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    fun print(message:String){
        Log.d("AdaptiveIcons","AdaptiveIcons main :"+message)
    }

}
