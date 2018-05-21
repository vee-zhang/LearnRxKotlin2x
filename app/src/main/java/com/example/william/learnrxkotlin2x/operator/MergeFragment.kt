package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable

/**
 * Created by william on 2018/2/23.
 */
class MergeFragment : BaseOperatorFragment() {

    /**
     * 最多合并4个Observable
     */
    fun merge1() {
        val observable1 = Observable.range(0, 10)
                .filter {
                    it % 2 == 0
                }
        val observable2 = Observable.range(0, 10)
                .filter {
                    it % 2 > 0
                }
        Observable.merge(observable1, observable2)
                .subscribe({
                    log("merge1", it.toString())
                })
    }

    fun merge2() {
        //这里先把一个Observable分成了两个Observable，一个发射奇数，一个发射偶数
        val observables = arrayListOf<Observable<Int>>()
        Observable.range(0, 10)
                .groupBy {
                    if (it % 2 == 0)true else false
                }
                .subscribe({
                    observables.add(it)
                })
        Observable.merge(observables)
                .subscribe({
                    log("merge2", it.toString())
                })
    }

    /**
     * @param maxConcurrency 又是最大并发数
     */
    fun merge3() {
        //这里先把一个Observable分成了两个Observable，一个发射奇数，一个发射偶数
        val observables = arrayListOf<Observable<Int>>()
        Observable.range(0, 10)
                .groupBy {
                    true
                }
                .subscribe({
                    observables.add(it)
                })
        Observable.merge(observables,5)
                .subscribe({
                    log("merge3", it.toString())
                })
    }
}