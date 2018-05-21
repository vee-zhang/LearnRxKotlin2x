package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.util.Log
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable


/**
 * A simple [Fragment] subclass.
 */
class FlatMapIterableFragment : BaseOperatorFragment() {

    fun flatMapIterable1(){
        Observable.range(0, 100)
                .flatMapIterable<Int> { it ->
                    arrayListOf(it)
                }
                .subscribe({
                    Log.d(TAG, "flatMapIterable-$it")
                })
    }

    fun flatMapIterable2(){
        Observable.range(0, 100)
                .flatMapIterable ({ it ->
                    arrayListOf(it)
                },{
                    t1, t2 ->
                    Log.d(TAG, "flatMapIterable2-t1:$t1\t${t1.javaClass}+t2:$t2${t2.javaClass}")
                    "$t1$t2"
                })
                .subscribe({
                    Log.d(TAG, "flatMapIterable2-$it")
                })
    }
}
