package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * A simple [Fragment] subclass.
 */
class EmptyFragment : BaseOperatorFragment() {

    var dd: Disposable? = null
    val observer = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            dd = d
        }

        override fun onNext(t: String) {
            Toast.makeText(context, t, Toast.LENGTH_SHORT).show()
        }

        override fun onError(e: Throwable) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        override fun onComplete() {
            Toast.makeText(context, "onComplate( )", Toast.LENGTH_SHORT).show()
        }
    }

    fun empty1() {
        //不发射任何实际数据，但是可正常终止
        Observable.empty<String>().subscribe(observer)
    }

    fun empty2() {
        //不发射任何实际数据，也不会终止（慎用！）
        if (dd == null || dd!!.isDisposed) {
            Observable.never<String>().doOnComplete {
                Toast.makeText(context, "永远不会onComplate${dd!!.isDisposed}", Toast.LENGTH_SHORT).show()
            }.subscribe(observer)
        } else {
            dd!!.dispose()
        }
    }

    fun empty3() {
        //不发射任何实际数据，但是可以error终止
        Observable.error<String>(Exception("不发射任何实际数据，但是可以error终止")).subscribe(observer)
    }
}