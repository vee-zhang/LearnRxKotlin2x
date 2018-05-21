package com.example.william.learnrxkotlin2x.operator


import android.app.Fragment
import android.widget.Toast
import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable


/**
 * A simple [Fragment] subclass.
 */
class RangeFragment : BaseOperatorFragment() {

    fun range1() {
        //第二个参数如果设为0将不发射任何数据，设为负数会报异常
        Observable.range(0,10).subscribe({
            Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
        })
    }
}// Required empty public constructor
