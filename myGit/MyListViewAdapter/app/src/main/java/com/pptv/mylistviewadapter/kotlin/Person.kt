package com.pptv.mylistviewadapter.kotlin

/**
 * @anthor LeiKang
 */
class Person {
    // 成员属性必须初始化
    var allByDefault: Int? =1
    val b :Int ? =1
    var stringRepresentation: String
        get() = this.toString()
        set(value) {
          // 解析字符串并赋值给其他属性
        }
    constructor(firstName :String ?)
    {
        var a = 1;
    }
    interface B {
        fun c() { print("B") } // 接口成员默认就是“open”的
        fun b()
    }

    fun setDataString(value :String)
    {
        value
    }
    open class C
    {
         fun f()
        {

        }
    }

    class A : C(),B
    {

        override fun b() {
            throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}