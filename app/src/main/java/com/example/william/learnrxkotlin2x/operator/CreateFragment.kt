package com.example.william.learnrxkotlin2x.operator

import android.app.Fragment
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * A simple [Fragment] subclass.
 */
class CreateFragment : BaseOperatorFragment() {

    /**
     *
     */
    fun create1() {
        //create操作符中必须至少调用一次onComplate( )或者onError( )，否则会引起观察者ANR
        //create操作符不管观察者是否重写了onComplate或者onError( )都要调用onComplate或者onOnNext
        Observable.create<Int>({
            if (!it.isDisposed) {
                for (i in 1..3) {
                    it.onNext(i)
                }
                it.onComplete()
            }
        }).subscribe({
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }, {

        }, {
            Toast.makeText(context, "onComplate( )", Toast.LENGTH_SHORT).show()
        })
    }

    fun create2() {
        //create操作符一旦被调用，就会只创建一个Observable,这个Observable可以多次订阅不同的观察者，
        // 但是观察者所订阅的都是同一个Observable，这点与defer操作符很不同
        var observer1Output = "观察者1输出："
//                var observer2Output = "观察者2输出："
        var i = 0
        val subject = PublishSubject.create<Int>()//创建observable
        subject.onNext(i)
//                i = 1
//                subject.onNext(i)
        //第一次订阅
        subject.subscribe({
            observer1Output += "${it}"
        }, {

        }, {
//            tv1.text = observer1Output
        })
        subject.onComplete()
    }
}// Required empty public constructor
