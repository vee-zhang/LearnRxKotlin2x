package com.example.william.learnrxkotlin2x.catalog

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.william.learnrxkotlin2x.MainActivity
import com.example.william.learnrxkotlin2x.R
import io.reactivex.subjects.*
import kotlinx.android.synthetic.main.fragment_blank.view.*

class SubjectFragment : Fragment(), View.OnClickListener {

    lateinit var activity: MainActivity
    lateinit var rootView: View

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.activity = getActivity() as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_blank, container, false)
        rootView.btn_iterable.setOnClickListener(this)
        rootView.btn2.setOnClickListener(this)
        rootView.btn3.setOnClickListener(this)
        rootView.btn4.setOnClickListener(this)
        rootView.btn5.setOnClickListener(this)
        rootView.btn6.setOnClickListener(this)
        return rootView
    }

    /*可以作为上游发射数据，也可以作为下游接收数据*/
    fun asyncSubjectOnComplate() {
        val subject = AsyncSubject.create<Int>()
        subject.subscribe({
            activity.showToast(it.toString())
        }, {

        }, {
            activity.showToast("onComplate")
        })

        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(3)
        subject.onComplete()
    }

    fun asyncSubjectOnError() {
        val subject = AsyncSubject.create<Int>()
        subject.subscribe({
            activity.showToast(it.toString())
        }, {
            activity.showToast(it!!.message!!)
        }, {
        })

        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(3)
        subject.onError(Exception("onError"))
    }

    fun behaviorSubjectOnComplate(){
        val subject = BehaviorSubject.createDefault("默认值")
        subject.subscribe({
            activity.showToast(it.toString())
        }, {
        }, {
        })
        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")
        //3发送后再订阅，依然能接收到3
        subject.subscribe({
            activity.showToast(it.toString())
        }, {
            activity.showToast(it!!.message!!)
        }, {
            activity.showToast("onComplate,你会发现没有1和2")
        })
        subject.onNext("4")
        subject.onNext("5")
        subject.onComplete()
    }

    fun behaviorSubjectOnError(){
        val subject = BehaviorSubject.create<Int>()
        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(3)
        subject.onError(Exception("onError"))
        //注意，这里是onError之后才订阅，但是依然能接收到onError事件
        subject.subscribe({
            activity.showToast(it.toString())
        }, {
            activity.showToast(it!!.message!!)
        }, {
            activity.showToast("onComplate")
        })
    }

    fun publishSubjectOnComplate(){
        val subject = PublishSubject.create<String>()
        subject.onNext("1")
        subject.onNext("2")
        subject.subscribe({
            activity.showToast(it)
        },{},{
            activity.showToast("onComplate,PublishSubject会丢失订阅之前的所有消息，所以没有1和2")
        })
        subject.onNext("3")
        subject.onNext("4")
        subject.onComplete()
    }

    fun replaySubject(){
        val subject = ReplaySubject.create<Int>()
        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(3)
        subject.onComplete()
        subject.subscribe({
            activity.showToast(it.toString())
        },{

        },{
            activity.showToast("onComplate，这次是在所有事件发射后才订阅的，但是replaySubject依然可重现所有的事件")
        })
    }

    override fun onClick(v: View) {
        v as Button
        when (v.id) {
            R.id.btn_iterable -> {
                asyncSubjectOnComplate()
            }
            R.id.btn2 -> {
                asyncSubjectOnError()
            }

            R.id.btn3 ->{
                behaviorSubjectOnComplate()
            }

            R.id.btn4 ->{
                behaviorSubjectOnError()
            }

            R.id.btn5 ->{
                publishSubjectOnComplate()
            }
            R.id.btn6 ->{
                replaySubject()
            }
        }
    }
}
