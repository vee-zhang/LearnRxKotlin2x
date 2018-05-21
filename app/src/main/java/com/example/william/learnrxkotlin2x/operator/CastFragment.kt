package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable

/**
 * Created by william on 2018/2/14.
 *
 * 超简版的map
 */
class CastFragment:BaseOperatorFragment() {
    fun cast1(){
        Observable.range(0,100)
                .cast(Long::class.java)
                .subscribe({
                    log("cast1","$it-${it::class}")
                })
    }
}