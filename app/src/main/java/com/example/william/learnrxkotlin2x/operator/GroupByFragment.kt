package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable


/**
 * A simple [Fragment] subclass.
 */
class GroupByFragment : BaseOperatorFragment() {

    fun groupBy1() {
        Observable.range(0, 100)
                .groupBy {
                    when {
                        it % 2 == 0 -> true
                        else -> false
                    }
                }
                .subscribe({
                    if (it.key!!) {
                        it.subscribe({
                            log("groupBy1", "偶数-$it")
                        })
                    } else {
                        it.subscribe({
                            log("groupBy1", "奇数-$it")
                        })
                    }
                })
    }

    /**
     * @param delayError 延迟错误处理，如果是正确的，则在每个组中，当前可观察到的异常会被延迟，直到这个组发出正常的值;
    如果错误，则该异常绕过组中的值并立即报告。
     */
    fun groupBy2() {
        toast("这个变体带个delayError，我玩不转了，郁闷！")
//        Observable.range(0, 100)
//                .groupBy({
//                    when {
//                        it % 5 == 0 -> Observable.error(Exception("出异常了！"))
//                        it % 2 == 0 -> Observable.just(true)
//                        else -> Observable.just(false)
//                    }
//                }, true)
//                .subscribe({
//                    var b = false
//                    it.key!!.subscribe({
//                        b = it
//                    }, {
//                        log("groupBy2", it)
//                    })
//                    if (b) {
//                        it.subscribe({
//                            log("groupBy2", "偶数-$it")
//                        })
//                    } else {
//                        it.subscribe({
//                            log("groupBy2", "奇数-$it")
//                        }, {
//                            log("groupBy2", it)
//                        })
//                    }
//                }, {
//                    log("groupBy2", it)
//                })
    }

    /**
     * 可以在分组的同时做转换
     */
    fun groupBy3() {
        Observable.range(0, 100)
                .groupBy({
                    when {
                        it % 2 == 0 -> true
                        else -> false
                    }
                }, {
                    "[$it]"
                })
                .subscribe({
                    if (it.key!!) {
                        it.subscribe({
                            log("groupBy3", "偶数-$it")
                        })
                    } else {
                        it.subscribe({
                            log("groupBy3", "奇数-$it")
                        })
                    }
                })
    }
}// Required empty public constructor
