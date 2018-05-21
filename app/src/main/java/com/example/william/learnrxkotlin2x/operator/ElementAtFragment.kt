package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable

/**
 * Created by william on 2018/2/22.
 */
class ElementAtFragment :BaseOperatorFragment(){

    fun elementAt1(){
        Observable.range(0,100)
                .elementAt(50)
                .subscribe({
                    log("elementAt1",it.toString())
                })
    }

    /**
     * 注意，一旦给了参数2，则elementAt会返回一个single对象，订阅这货，只能接收到OnSuccess或者onError,个人感觉意义不大
     */
    fun elementAt2(){
        Observable.range(0,100)
                .elementAt(101,250)
                .subscribe({
                    log("elementAt2",it.toString())
                },{})
    }
}