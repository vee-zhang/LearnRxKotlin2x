package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.Disposable
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

/**
 * Created by william on 2018/2/15.
 * 这货与buffer比较类似,但是区别是，buffer返回一个list，而window返回的是一个Observable，所以更加扁平。我们可以顺势在这个Observable
 * 中做更多的处理，用起来很方便，代码也更加精简。所以比起buffer我个人更加推荐使用window方法。
 */
class WindowFragment : BaseOperatorFragment() {

    var num = 0

    fun window1() {
        Observable.range(0, 100)
                .window(10)
                .subscribe({
                    ++num
                    it.subscribe({
                        log("window1", "第${num}次订阅：$it")
                    })
                }, {}, {
                    num = 0
                })
    }

    /**
     * 注意看输出，会发现少了几个数，因为被跳过了
     */
    fun window2() {
        Observable.range(0, 100)
                .window(4, 5)
                .subscribe({
                    ++num
                    it.subscribe({
                        log("window2", "第${num}次订阅：$it")
                    })
                }, {}, {
                    num = 0
                })
    }

    fun window3() {
        Observable.range(0, 100)
                .window(4, 5, 5)
                .subscribe({
                    ++num
                    it.subscribe({
                        log("window3", "第${num}次订阅：$it")
                    })
                }, {}, {
                    num = 0
                })
    }

    var disposable: Disposable? = null
    fun window4() {
        if (disposable == null || disposable!!.isDisposed) {
            disposable = Observable.interval(1, TimeUnit.SECONDS)
                    .window(5, TimeUnit.SECONDS)
                    .subscribe({
                        ++num
                        it.subscribe({
                            log("window4", "第${num}次订阅：$it")
                        })
                    })
        } else {
            disposable!!.dispose()
            num = 0
        }
    }

    /**
     * 可以看到，加入第三个参数后，就可限制下游每次接收到的Observable的个数，这个可以跟{@code window4()}方法做对比
     */
    fun window5() {
        if (disposable == null || disposable!!.isDisposed) {
            disposable = Observable.interval(1, TimeUnit.SECONDS)
                    .window(5, TimeUnit.SECONDS, 4)
                    .subscribe({
                        ++num
                        it.subscribe({
                            log("window5", "第${num}次订阅：$it")
                        })
                    })
        } else {
            disposable!!.dispose()
            num = 0
        }
    }

    /**
     * @param 3 count 每个窗口list的size
     * @param 4 restart 窗口达到容量限制时是否重启计时器(使下游接收的更加整齐？？搞了半天每看出区别来。。。）
     *
     */
    fun window6() {
        if (disposable == null || disposable!!.isDisposed) {
            disposable = Observable.interval(1, TimeUnit.SECONDS)
                    .window(10, TimeUnit.SECONDS, 4, true)
                    .subscribe({
                        ++num
                        it.subscribe({
                            log("window6", "第${num}次订阅：$it")
                        })
                    })
        } else {
            disposable!!.dispose()
            num = 0
        }
    }

    /**
     * 危险，这个写法，我实际测下来，尽管调用了dispose()，但下游却不能被真正的dispose，因为window()生成的Observable没有被dispose。
     */
    var timerDisposable:Disposable? = null
    fun window7() {
//        var timerDisposable:Disposable? = null
        if (disposable == null || disposable!!.isDisposed) {
            disposable = Observable.interval(1, TimeUnit.SECONDS)
                    .window(ObservableSource<Long> {
                        Observable.timer(5, TimeUnit.SECONDS)
                    })
                    .subscribe({
                        ++num
                        if (timerDisposable != null || timerDisposable!!.isDisposed) {
                            timerDisposable = it.subscribe({
                                log("window7", "第${num}次订阅：$it")
                            })
                        }else{

                        }
                    })
        } else {
            disposable!!.dispose()
            num = 0
        }
    }
}