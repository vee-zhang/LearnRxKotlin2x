package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Created by william on 2018/2/22.
 * 通过时间来限流，源Observable每次发射出来一个数据后就会进行计时，
如果在设定好的时间结束前源Observable有新的数据发射出来，这个数据就会被丢弃，同时重新开始计时。
如果每次都是在计时结束前发射数据，那么这个限流就会走向极端：只会发射最后一个数据。默认在computation调度器上执行。

 感觉这货跟{@ling Debounce}还是很像
 */
class ThrottleWithTimeoutFragment:BaseOperatorFragment() {

    var disposable:Disposable? = null

    fun throttleWithTimeout(){
        if (disposable == null || disposable!!.isDisposed){
            disposable = Observable.range(0,1000)
                    .throttleFirst(5, TimeUnit.MICROSECONDS)
                    .subscribe({
                        log("throttleWithTimeout",it.toString())
                    })
        }else{
            disposable!!.dispose()
        }
    }
}