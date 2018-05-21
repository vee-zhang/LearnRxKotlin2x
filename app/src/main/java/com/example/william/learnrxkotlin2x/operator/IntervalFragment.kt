package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
class IntervalFragment : BaseOperatorFragment() {

    private var disposable: Disposable? = null
    fun interval() {
        if (disposable == null || disposable!!.isDisposed) {
            disposable = Observable.interval(1, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.computation())//默认在cpu线程上订阅，可不写
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                    })
        } else {
            disposable!!.dispose()
        }
    }
}// Required empty public constructor
