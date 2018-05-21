package com.example.william.learnrxkotlin2x.operator


import android.util.Log
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.rxkotlin.toObservable
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
/**
 * 默认调度器computation
 */
class BufferFragment : BaseOperatorFragment() {

    /**
     * 最普通的用法，count = 3,则会缓冲成长度为3的list再发送
     */
    fun buffer1() {
        Observable.range(0, 9)
                .buffer(3)
                .subscribe({
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                })
    }

    /**
     * 最普通的用法，count = 2,skip=3,则会跳过第3个item再缓冲长度为2的list
     * 格式如：{[0,1],[3,4],[6,7]}
     * 如果skip < count,则会发生数据重叠，如：{[0,1],}
     * 如果skip > count，则会出现间隙
     */
    fun buffer2() {
        Observable.range(0, 9)
                .buffer(2, 3)
                .subscribe({
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                })
    }

    /**
     * 自定义缓冲规则，每当参数里的Observable发射一个item,buffer就会创建一个list采集这期间缓冲的real item。
     * buffer(boundary)
     */
    fun buffer3() {
        intArrayOf().toObservable()
        Observable.range(0, 1000)
                .buffer(Observable.interval(1, TimeUnit.MILLISECONDS))
                .subscribe({
                    Log.d("buffer", it.toString())
                })
    }

    /**
     *自定义缓冲list的开始和结束
     * 当参数1的Observabel发射一个item时，会创建一个新的list并且把item追加到list中，然后把item作为参数2的方法实参传入
     * 我们可以在参数2的方法中做条件判断
     * 当参数2返回的Obervable发射一个item时，Buffer会把参数1创建的list发射出去
     */
    var disposable4: Disposable? = null

    fun buffer4() {
        if (disposable4 == null || disposable4!!.isDisposed) {
            disposable4 = Observable.interval(1, TimeUnit.SECONDS)
                    .buffer(Observable.interval(5, TimeUnit.SECONDS), Function<Long, ObservableSource<*>> {
                        if (it % 2 == 0L) {
                            Observable.timer(5, TimeUnit.SECONDS)
                        } else {
                            Observable.timer(10, TimeUnit.SECONDS)
                        }
                    }).subscribe({
                Log.d("buffer", it.toString())
            })
        } else {
            disposable4!!.dispose()
        }
    }

    /**
     *自定义缓冲list的开始和结束
     * 当参数1的Observabel发射一个item时，会创建一个新的list并且把item追加到list中，然后把item作为参数2的方法实参传入
     * 我们可以在参数2的方法中做条件判断
     * 当参数2返回的Obervable发射一个item时，Buffer会把参数1创建的list发射出去
     */


    /**
     *
     * @param Callable<U> bufferSupplier 这个货需要返回一个Connection的子类，这个子类的类型必须跟buffer原始的发射物类型和范型
     * 完全一致。返回的这个子list会把buffer本次的item（原始list）直接addAll进来，最后buffer会发射这个子list。
     */
    var disposable5: Disposable? = null

    fun buffer5() = if (disposable5 == null || disposable5!!.isDisposed) {
        disposable5 = Observable.interval(1, TimeUnit.SECONDS)
                .buffer(Observable.interval(5, TimeUnit.SECONDS),
                        Function<Long, ObservableSource<*>> {
                            if (it % 2 == 0L) {
                                Observable.timer(5, TimeUnit.SECONDS)
                            } else {
                                Observable.timer(10, TimeUnit.SECONDS)
                            }
                        },
                        Callable<ArrayList<Long>> {
                            arrayListOf(0L, 0L, 0L)
                        }).subscribe({
            Log.d("buffer", it.toString())
        })
    } else {
        disposable5!!.dispose()
    }

    var disposable6: Disposable? = null
    fun buffer6() = if (disposable6 == null || disposable6!!.isDisposed) {
        disposable6 = Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .buffer(Observable.interval(2, TimeUnit.SECONDS))
                .subscribe { Log.d("buffer", "buffer1->" + it.toString()) }
    } else {
        disposable6!!.dispose()
    }
}

// Required empty public constructor
