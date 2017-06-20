package com.pptv.mylistviewadapter.kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.pptv.mylistviewadapter.R

class Main2Activity : AppCompatActivity() {

    var data: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById(R.id.aa).setOnClickListener { this.longToast("aaa") }
        var numbers = listOf(Person("aaa"),Person("aaa"))
        numbers.apply {Person("aaa")}
        numbers.apply { Person("aaab")}
        numbers.forEach { Log.e("TAg",it.name)}
        val a: Int = 1
        val c:Int ? = 1 // 一个装箱的 Int (java.lang.Integer)
        val b: Long? = a.toLong() // 隐式转换产生一个装箱的 Long (java.lang.Long)

        val a1 :Int ?  = 2
        val l = 1L + 3 // Long + Int => Long
        this.longToast("");
    }

    fun Context.longToast(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    class Person(var name: String = "张三")
}
