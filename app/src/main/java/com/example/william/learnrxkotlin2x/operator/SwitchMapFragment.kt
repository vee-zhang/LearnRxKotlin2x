package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable

/**
 * Created by william on 2018/2/14.
 *
 * 当原始Observable发射一个新的数据（Observable）时，它将取消订阅并停止监视产生执之前那个数据的Observable，只监视当前这一个。
 */
class SwitchMapFragment:BaseOperatorFragment() {
    fun switchMap1(){
        Observable.just(0,100)
                .switchMap {
                    Observable.just(it.toString())
                }
                .subscribe({
                    log("switchMap1",it)
                })
    }
}