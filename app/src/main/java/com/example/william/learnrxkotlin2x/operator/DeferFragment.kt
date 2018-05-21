package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe


/**
 * A simple [Fragment] subclass.
 */
class DeferFragment : BaseOperatorFragment() {

    fun defer1() {
        var num = 0
        //在调用create方法时就创建了Observable,而且从始至终只有一个observable
        val observable = Observable.create<String>({
            it.onNext("结果为：${num}")
            it.onComplete()
        })
        ++num
        observable.subscribe({
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }, {}, {})
    }
    fun defer2(){
        var num = 0
        val observable = Observable.defer {
            Observable.create<String>(ObservableOnSubscribe {
                it.onNext("结果为：${num}")
                it.onComplete()
            })
        }
        ++num
        //在订阅时才创建observable,而且每次订阅都会创建新的observable
        observable.subscribe({
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }, {}, {})
    }
}// Required empty public constructor
