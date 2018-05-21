package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import java.util.concurrent.Callable


/**
 * A simple [Fragment] subclass.
 */
class FromFragment : BaseOperatorFragment() {

    fun from1() {
        val list = arrayListOf<Int>(0, 1, 2, 3)
        Observable.fromIterable(list).subscribe({
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }, {}, {
            Toast.makeText(context, "OnComplate( ),fromIterable可循环便利输出每一项", Toast.LENGTH_SHORT).show()
        })
        //不能监听变化，所以下面的4和5不会触发订阅
        list.add(4)
        list.add(5)
    }

    fun from2() {
        val array = arrayOf(0, 1, 2, 3)
        Observable.fromArray(array).subscribe({
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }, {}, {
            Toast.makeText(context, "OnComplate( )，fromArray可直接返回集合或数组", Toast.LENGTH_SHORT).show()
        })
    }

    fun from3() {
        val callable = object : Callable<Boolean> {
            override fun call(): Boolean {
                return false
            }
        }
        Observable.fromCallable(callable).subscribe({
            Toast.makeText(context, "运行了", Toast.LENGTH_SHORT).show()
        }, {}, {
            Toast.makeText(context, "OnComplate( )", Toast.LENGTH_SHORT).show()
        })
    }
}// Required empty public constructor
