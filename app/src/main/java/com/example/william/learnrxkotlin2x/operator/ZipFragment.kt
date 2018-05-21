package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function

/**
 * Created by william on 2018/2/24.
 */
class ZipFragment : BaseOperatorFragment() {

    fun zip1() {
        val observable1 = Observable.range(0, 10)
                .filter {
                    it % 2 == 0
                }
                .map {
                    it.toString()
                }
        val observable2 = Observable.range(0, 10)
                .filter {
                    it % 2 > 0
                }
                .map {
                    it.toString()
                }
        Observable.zip<String, String, String>(observable1, observable2, BiFunction { t1, t2 ->
            "t1:$t1\tt2:$t2"
        })
                .subscribe({
                    log("zip1", it)
                })
    }

    /**
     * 这种写法可以对一个集合做处理，在参数2的aply方法中做处理。
     * 这个方法的实参是一个集合，集合的每一个元素是各个Observable同等位置的item，具体查看log。
     */
    fun zip2() {
        //这里先把一个Observable分成了两个Observable，一个发射奇数，一个发射偶数
        val observables = mutableListOf<Observable<Int>>()
        Observable.range(0, 10)
                .groupBy {
                    when {
                        it % 3 == 0 -> 1
                        it % 3 == 1 -> 2
                        it % 3 == 2 -> 3
                        else -> 4
                    }
                }
                .subscribe({
                    observables.add(it)
                })
        Observable.zip(observables, Function<Array<Any>, String> {
            it.forEach {
                log("zip2", "zip阶段：$it")
            }
            it[0].toString()
        })
                .subscribe({
                    log("zip2", it)
                })
    }
}