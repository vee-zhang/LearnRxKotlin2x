package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
class TimerFragment : BaseOperatorFragment() {

    fun timer1() {
        //在一个给定的延迟后发射0
        Observable.timer(3,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
                },{},{
                    Toast.makeText(context,"onComplate(),只会发射一个整数0",Toast.LENGTH_SHORT).show()
                })
    }
}// Required empty public constructor
