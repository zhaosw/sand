package com.mxnavi.adaptiveicons.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.mxnavi.adaptiveicons.MainActivity
import com.mxnavi.adaptiveicons.R
import com.mxnavi.adaptiveicons.base.BaseFragment
import com.mxnavi.adaptiveicons.opengl.DrawFragment
import com.mxnavi.adaptiveicons.opengl.es.DrawFragment1
import com.mxnavi.adaptiveicons.scheduled.Scheduled
import com.mxnavi.adaptiveicons.scheduled.ThreadPoolManager
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by Administrator on 2017/12/26 0026.
 */
class MainFragment : BaseFragment(),View.OnClickListener {

    companion object {
        val TAG:String = "MainFragment"
    }

    var mMainActivity: MainActivity? = null

    var rootView: View? = null

    var mDrawFragment: DrawFragment? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mMainActivity = activity as MainActivity?
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getFragmentView(inflater,container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidget()
    }

    fun reSetText(str:String){
        sample_text.text = str
    }

    fun getFragmentView(inflater: LayoutInflater?, container: ViewGroup?):View?{
        if(rootView == null){
            if (inflater != null) {
                rootView = inflater.inflate(R.layout.fragment_main,container,false)
            }
        }
        return rootView
    }

    fun initWidget(){
        button.text = "Kotlin"
        sample_text.text = sample_text.text.toString()+sum(2,1)
        sample_text.text = sample_text.text.toString()+"\n"+parseAnd("12")

        button.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
    }

    fun sum(a:Int,b:Int): Int{
        return a+b
    }

    fun parseInt(str:String):Int?{
        if(str.isNullOrEmpty())return null
        return str.toInt()
    }

    fun parseAnd(str:String): Int? {
        if(str.isNullOrEmpty()){
            val andInt = parseInt(str)
            return (andInt!!+andInt!!)
        }
        return 0
    }

    var count = 1

    fun startOpenGL(){
        mDrawFragment = DrawFragment()
        mMainActivity!!.supportFragmentManager.beginTransaction().add(R.id.main_frame,mDrawFragment, DrawFragment.TAG)
                .addToBackStack(null).commit()
    }

    fun startOpenGLES(){
        val drawFragment1 = DrawFragment1()
        mMainActivity!!.supportFragmentManager.beginTransaction().add(R.id.main_frame,drawFragment1, DrawFragment1.TAG)
                .addToBackStack(null).commit()
    }

    override fun onClick(v: View?) {
        if(v == null)return
        when(v.getId()){
            R.id.button ->{
                startOpenGL()
            }
            R.id.button1 ->{
                startOpenGLES()
            }

            R.id.button2 ->{
                ThreadPoolManager.getInstance().shutDown()
            }

            R.id.button3 ->{
                ThreadPoolManager.getInstance().shutDownNow()
            }
        }
    }

}