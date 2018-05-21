package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable

/**
 * Created by william on 2018/2/14.
 * scan操作符通过一个以函数处理第一个item，此后他每次都会把上次处理结果和本次的item都传给函数进行处理。
 */
class ScanFragment : BaseOperatorFragment() {
    fun scan1() {
        Observable.just("我勒个去", "你勒个去", "他勒个去", "我擦", "郁闷", "我晕", "擦了")
                .scan { t1: String, t2: String ->
                    log("scan1", "t1-$t1\tt2-$t2")
                    "${t2}处理后"
                }
                .subscribe({
                    log("scan1", "结果：$it")
                })
    }

    /**
     * @param initialValue 初始值，设置了这个初始值之后，会先发射这个初始值，也就是说初始值会成为第一个item
     */
    fun scan2() {
        Observable.just("我勒个去", "你勒个去", "他勒个去", "我擦", "郁闷", "我晕", "擦了")
                .scan("我是初始值！",
                        { t1: String, t2: String ->
                            log("scan1", "t1-$t1\tt2-$t2")
                            "${t2}处理后"
                        })
                .subscribe({
                    log("scan1", "结果：$it")
                })
    }
}