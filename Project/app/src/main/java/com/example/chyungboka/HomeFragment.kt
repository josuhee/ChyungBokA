package com.example.chyungboka

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
//import android.widget.ImageView;


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    //viewpager 설정
    //var state:String? = null
    //UIThread U

    var currentPosition =1
    var mainActivity:MainActivity?=null
    var mContext: Context?=null




    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity=context as MainActivity
        mContext=context
    }

    //핸들러 설정
    //ui 변경하기
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
       //if(state.equals("DeActive"))
         //  break
    }

    //페이지 변경하기
    fun setPage(){
        if(currentPosition==5) currentPosition=1
        pager.setCurrentItem(currentPosition,true)
        currentPosition+=1
    }

    //3초 마다 페이지 넘기기
    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                Thread.sleep(3000)
                handler.sendEmptyMessage(0)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home,container,false)
        //어댑터 연결하기
        val adapter=ViewPagerAdapter()
        view.pager.adapter=adapter

        //state = "Active"
        //버튼 클릭시 다음 page로 바로
        /*
        view.nextButton.setOnClickListener {
            currentPosition+=1
            view.pager.setCurrentItem(currentPosition, true)
        }
        */

        //홈페이지 연결하기
        view.imageView1.setImageResource(R.drawable.government24_icon)
        view.imageView2.setImageResource(R.drawable.worknet_icon)
        view.imageView3.setImageResource(R.drawable.suwonsi_icon)
        view.imageView4.setImageResource(R.drawable.yongin_icon)

        view.imageView1.setOnClickListener(View.OnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gov.kr"))
            startActivity(intent)
        })
        view.imageView2.setOnClickListener(View.OnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.work.go.kr"))
            startActivity(intent)
        })
        view.imageView3.setOnClickListener(View.OnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.yongin.go.kr"))
            startActivity(intent)
        })
        view.imageView4.setOnClickListener(View.OnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gov.kr"))
            startActivity(intent)
        })
        //뷰페이저 넘기는 쓰레드
        val thread=Thread(PagerRunnable())
        thread.start()
        return view
    }

    /*
    fun onStop(){
        super.onStop()
        state = "DeActive"
        Thread.interrupt()
    }
    */
}