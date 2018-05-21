package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.ofType
import java.util.concurrent.TimeUnit

/**
 * Created by william on 2018/2/22.
 */
class FilterFragment : BaseOperatorFragment() {

    var disposable:Disposable? = null

    fun filter() {
        Observable.range(0, 100)
                .filter {
                    it % 2 == 0
                }
                .subscribe({
                    log("filter1", it.toString())
                })
    }

    fun ofType() {
        open class Person(val name: String)
        class Teacher(name: String) : Person(name)
        class Student(name: String) : Person(name)

        val list = ArrayList<Person>()

        for (i in 0..100) {
            if (i % 2 == 0) {
                list.add(Teacher("教师$i"))
            } else {
                list.add(Student("学生$i"))
            }
        }

        Observable.fromIterable(list)
                .ofType<Student>()
                .subscribe({
                    log("ofType", it.toString())
                })
    }

    /**
     * 如果Observable什么都没有发射，那么就会发射一个默认值
     */
    fun first(){
        Observable.range(0,100)
                .first(-1)
                .subscribe({
                    log("first",it.toString())
                },{})
    }

    fun single(){
        Observable.range(0,100)
                .single(-1)
                .subscribe({
                    log("first",it.toString())
                },{})
    }

    fun firstOrError(){
        Observable.range(0,100)
                .firstOrError()
                .subscribe({
                    log("firstOrError",it.toString())
                },{})
    }

    fun take1(){
        Observable.range(0,100)
                .take(10)
                .subscribe({
                    log("take1",it.toString())
                },{})
    }

    fun take2(){
        Observable.range(0,1000)
                .take(1,TimeUnit.MICROSECONDS)
                .subscribe({
                    log("take2",it.toString())
                },{})
    }


    fun take3(){
        Observable.range(0,1000)
                .takeLast(10)
                .subscribe({
                    log("take3",it.toString())
                },{})
    }

    /**
     * 忽略所有的item，只接收终止的通知
     */
    fun ignoreElements(){
        Observable.range(0,1000)
                .ignoreElements()
                .subscribe({
                    log("take3","完成了！！")
                },{})
    }

    fun last(){
        Observable.range(0,1000)
                .last(-1)
                .subscribe({
                    log("take3",it.toString())
                },{})
    }

    /**
     * Sample操作符定时查看一个Observable，然后发射自上次采样以来它最近发射的数据。
     * 可以通过第二个参数设置是否发射最后一个item
     */
    fun sample(){
        if (disposable == null || disposable!!.isDisposed){
            disposable = Observable.interval(1,TimeUnit.SECONDS)
                    .sample(2,TimeUnit.SECONDS)
                    .subscribe({
                        log("sample",it.toString())
                    })
        }else{
            disposable!!.dispose()
        }
    }


    /**
     * ThrottleFirst操作符的功能类似，但不是发射采样期间的最近的数据，而是发射在那段时间内的第一项数据。
     */
    fun throttleLast(){
        if (disposable == null || disposable!!.isDisposed){
            disposable = Observable.interval(1,TimeUnit.SECONDS)
                    .throttleLast(2,TimeUnit.SECONDS)
                    .subscribe({
                        log("throttleLast",it.toString())
                    })
        }else{
            disposable!!.dispose()
        }
    }

    fun skip1(){
        Observable.range(0,100)
                .skip(50)
                .subscribe({
                    log("skip1",it.toString())
                })
    }

    fun skip2(){
        Observable.range(0,1000)
                .skip(1,TimeUnit.MICROSECONDS)
                .subscribe({
                    log("skip1",it.toString())
                })
    }
}