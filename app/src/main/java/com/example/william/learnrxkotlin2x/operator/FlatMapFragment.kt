package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.util.Log
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable


/**
 * A simple [Fragment] subclass.
 */
class FlatMapFragment : BaseOperatorFragment() {

    fun flatMap1() {
        Observable.range(0, 100)
                .flatMap {
                    Observable.just(it.toString())
                }
                .subscribe({
                    Log.d(TAG, "flatMap1-$it-${it.javaClass}")
                })
    }

    /**
     * @param Function 用来把一个item转换成另一个item，再包装成一个Observable并返回
     * @param delayErrors 官方解释：参数1Function中的Observable如果产生了onError事件：true-延迟到全部的转换都结束时再处理；false-立即停止序列
     */
    fun flatMap2() {
        Observable.range(0, 100)
                .flatMap({
                    when{
                        it == 50 ->Observable.error(Exception("出现转换异常"))
                        else -> Observable.just(it)
                    }
                }, true)
                .subscribe({
                    Log.d(TAG, "flatMap2-$it-${it.javaClass}")
                }, {
                    Log.d(TAG, "flatMap2-异常:${it.message}")
                }, {
                    Log.d(TAG, "flatMap2-Complate")
                })
    }

    /**
     * @param Function 用来把一个item转换成另一个item，再包装成一个Observable并返回
     * @param delayErrors 参数2Function中的Observable如果产生了onError事件：ture-延迟到全部的转换都结束时再处理；false-理解停止序列
     * @param maxConcurrency 最大并发，也即同时转换的最大个数，这个数值越大转换就越快也越占内存
     */
    fun flatMap3() {
        Observable.range(0, 100)
                .flatMap({
                    Observable.just(it).doOnNext {
                        Log.d(TAG, "flatMap3转换-$it-${it.javaClass}")
                    }
                }, true, 10)
                .subscribe({
                    //                    Log.d(TAG, "flatMap3-$it-${it.javaClass}")
                })
    }

    /**
     * @param Function 用来把一个item转换成另一个item，再包装成一个Observable并返回
     * @param delayErrors 参数2Function中的Observable如果产生了onError事件：ture-延迟到全部的转换都结束时再处理；false-理解停止序列
     * @param maxConcurrency 最大并发，也即同时转换的最大个数，这个数值越大转换就越快也越占内存
     * @param flatMapSize 从每个Obervable中预取的数量
     */
    fun flatMap4() {
        Observable.range(0, 100)
                .flatMap({
                    Log.d(TAG, "flatMap4转换-$it-${it.javaClass}")
                    Observable.just(it)
                }, true, 2, 10)
                .subscribe({
                    //                    Log.d(TAG, "flatMap4-$it-${it.javaClass}")
                })
    }

    /**
     * 对原始Observable的每一个item和每一个notification进行合并，如何理解呢？
     * 1.flatMap会把SourceObservable发射的每一个item包装成一个个独立的新Observable。
     * 2.对这些独立的Observable在发射过程中产生的多个onError和多个onComplete事件进行合并，成为一个onError和一个onComplete事件（我猜其实都是取第一个）
     * 3.最后再一起发送时就成了N个OnNext，和一个OnError和一个OnComplete
     * 用起来好像跟flatMap2()差不多，但是这个变体暴露出了转换的onError和onComplete事件，we can do anythings！比如上传了文件之后在onCompleteSupplier
     * 中回调通知服务器更新，如果到这一步失败，就error出一个Exception，在Oberver中将直接执行onError方法，而不是正常的onComplete方法。
     */
    fun flatMap5() {
        Observable.range(0, 100)
                .flatMap({
                    Observable.just(it.toString())
                }, {
                    Log.d(TAG, "flatMap5转换中-${it.message}")
                    Observable.just(it)
                }, {
                    Observable.error(Exception("快成功的时候失败了！"))
//                    Observable.just(0)
                })
                .subscribe({
                    Log.d(TAG, "flatMap5-$it-${it.javaClass}")
                }, {
                    Log.d(TAG, "flatMap5异常-${it.message}")
                }, {
                    Log.d(TAG, "flatMap5-Complete")
                })
    }

    fun flatMap6() {
        Observable.range(0, 100)
                .flatMap({
                    Observable.just(it)
                }, {
                    Observable.error(it)
                }, {
                    Observable.just(0)
                }, 10)
                .subscribe({
                    Log.d(TAG, "flatMap6--$it-${it.javaClass}")
                }, {
                    Log.d(TAG, "flatMap6-${it.message}")
                }, {
                    Log.d(TAG, "flatMap6-Complate")
                })
    }

    fun flatMap7() {
        Observable.range(0, 100)
                .flatMap({
                    Observable.just(it)
                }, 10)
                .subscribe({
                    Log.d(TAG, "flatMap7-$it-${it.javaClass}")
                }, {
                    Log.d(TAG, "flatMap7-${it.message}")
                }, {
                    Log.d(TAG, "flatMap7-Complate")
                })
    }

    /**
     *@param resultSelector 二次转换？转换完之后可以获取到原始数据和转换后的数据，最后经过处理返回新的数据
     */
    fun flatMap8() {
        Observable.range(1, 100)
                .flatMap({ it ->
                            Observable.just("转换后-$it")
                        },
                        { t1, t2 ->
                            Log.d(TAG, "flatMap8-t1:$t1\t${t1.javaClass}+t2:$t2${t2.javaClass}")
                            "$t1$t2"
                        }).subscribe {
            Log.d(TAG, "flatMap8-$it-${it.javaClass}")
        }
    }
}// Required empty public constructor


