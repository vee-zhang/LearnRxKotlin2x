package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable
import io.reactivex.functions.BiPredicate
import io.reactivex.functions.Function

/**
 * Created by william on 2018/2/22.
 */
class DistinctUntilChangedFragment :BaseOperatorFragment() {

    fun distinctUntilChanged1(){
        Observable.just(0,0,1,2,2,2,3,4,5,5)
                .distinctUntilChanged()
                .subscribe({
                    log("distinctUntilChanged1",it.toString())
                })
    }

    fun distinctUntilChanged2(){
        data class Student(val firstName:String,val lastName:String)
        val students = arrayListOf<Student>()
        var firstName = ""
        for (i in 0..10){
            if (i<5){
                firstName = "张"
            } else{
                firstName = "李"
            }
            students.add(Student(firstName,"${i}郎"))
        }
        Observable.fromIterable(students)
                .distinctUntilChanged({it->
                    it.firstName
                })
                .subscribe({
                    log("distinct1",it.toString())
                })
    }

    /**
     * 当前的item去跟前一个item比较
     * @param t1 前一个item
     * @param t2 当前item
     */
    fun distinctUntilChanged3(){
        Observable.just(0,0,1,2,2,2,3,4,5,5)
                .distinctUntilChanged({ t1, t2 ->
                    log("distinctUntilChanged3","t1:${t1}\tt2:${t2}")
                    true
                })
                .subscribe({
                    log("distinct1",it.toString())
                })
    }
}