package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable

/**
 * Created by william on 2018/2/14.
 */
class ConcatMapFragment : BaseOperatorFragment() {
    fun concatMap1() {
        Observable.range(0, 100)
                .concatMap {
                    Observable.just(it.toString())
                }
                .subscribe({
                    log("concatMap1",it)
                })
    }

    /**
     *
     * @param prefetch 预加载数量
     */
    fun concatMap2(){
        Observable.range(0,100)
                .concatMap({
                    Observable.just(it.toString())
                },10)
                .subscribe({
                    log("concatMap2",it)
                })
    }
}