package com.example.william.learnrxkotlin2x.operator

import com.example.william.learnrxkotlin2x.BaseOperatorFragment
import io.reactivex.Observable

/**
 * Created by william on 2018/2/22.
 */
class DistinctFragment:BaseOperatorFragment() {

    fun distinct1(){
        Observable.just(1,2,1,1,1,2,3)
                .distinct()
                .subscribe({
                    log("distinct1",it.toString())
                })
    }

    /**
     * 比较的是item的key，而不是item本身，所谓key可以是对象的一个属性，直接返回这个属性就行了，distinct操作符会自动比较
     */
    fun distinct2(){
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
                .distinct {
                    it.firstName
                }
                .subscribe({
                    log("distinct1",it.toString())
                })
    }
}