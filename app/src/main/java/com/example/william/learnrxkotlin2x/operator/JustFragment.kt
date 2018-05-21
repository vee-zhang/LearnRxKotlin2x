package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable


/**
 * A simple [Fragment] subclass.
 */
class JustFragment : BaseOperatorFragment() {

    fun just1() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribe({
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                })
    }

    fun just2() {
        try {
            Observable.just(null)//发送null
                    .subscribe({
                        Toast.makeText(context, "收到了null", Toast.LENGTH_SHORT).show()
                    })
        } catch (e: NullPointerException) {
            Toast.makeText(context, "收到了null,但是kotlin不允许null，所以这里会有NPE异常！", Toast.LENGTH_SHORT).show()
        }
    }
}// Required empty public constructor
