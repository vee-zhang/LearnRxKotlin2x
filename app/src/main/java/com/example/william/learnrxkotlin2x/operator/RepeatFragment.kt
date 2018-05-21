package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.util.Log
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
//todo repeat操作符并没有创建新的Observable,所以不能放到创建类型中，应该自己单独搞一个类型
class RepeatFragment : BaseOperatorFragment() {

    fun repeat1() {
        Observable.range(1, 3)//创建Observable
                .repeat(2)//重复发送1次原始的Observable
                .subscribe({
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }, {
                    //仅对onComplate()生效，也即使说执行了onError()后将不在重复订阅
                }, {
                    Toast.makeText(context, "onComplate( )触发再次订阅！", Toast.LENGTH_SHORT).show()
                })
    }


    fun repeat2() {
        Observable.range(1, 3)
                .repeatWhen {
                    //定制重复订阅的条件
//                            Observable.range(0, 3)//由于erpeatWhen只是重复订阅原始的Observable，并没有new新的，所以这里不能再使用创建类型的操作符！
                    it.delay(5, TimeUnit.SECONDS)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("测试", "onNext${it}")
                }, {}, {
                    Toast.makeText(context, "onComplate( )触发再次订阅！", Toast.LENGTH_SHORT).show()
                    Log.d("测试", "onComplate")
                })
    }

    //todo 不会用
    fun repeat3() {

    }

    //todo 不会用
    fun repeat4() {

    }
}// Required empty public constructor
