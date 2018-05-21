package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable

/**
 * Created by william on 2018/2/14.
 */
class MapFragment:BaseOperatorFragment() {

    fun map1(){
        Observable.range(0,100)
                .map {
                    "[$it]"
                }
                .subscribe({
                    log("map1",it)
                })
    }
}