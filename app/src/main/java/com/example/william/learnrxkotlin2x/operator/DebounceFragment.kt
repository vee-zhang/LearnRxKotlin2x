package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Created by william on 2018/2/22.
 */
class DebounceFragment : BaseOperatorFragment() {

    var disposable:Disposable? = null

    /**
     * 注意看球线图，这货实际上可以认为是在他规定的计时内，只发送最后一个item
     * 很适合做一些类似于：打字的即时监听，按两下back键退出应用这样的功能
     */
    fun debounce() {
        if (disposable == null || disposable!!.isDisposed){
            disposable = Observable.range(0,1000)
                    .debounce(5,TimeUnit.MICROSECONDS)
                    .subscribe({
                        log("debounce",it.toString())
                    })
        }else{
            disposable!!.dispose()
        }
    }
}