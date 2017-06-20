package com.pptv.mylistviewadapter.kotlin

/**
 * @anthor LeiKang
 */
class BaseRuleKotlin {
      /**
       * a 形参，
       *
       * ：int 返回值是Int
       */
    fun sum(a: Int, b: Int): Int {

          val items = listOf("apple", "banana", "kiwi")
          for (index in items.indices) {
              println("item at $index is ${items[index]}")
          }
        return a + b
    }
    //lambda 的应用
    fun main(args: Array<String>) {
        val fruits = listOf("banana", "avocado", "apple", "kiwi")
        fruits
                .filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }
    }
    fun sum2(a: Int, b: Int) = a + b


      fun main():String
      {
          val a: Int = 1  // 立即赋值
          val b = 2   // 自动推断出 `Int` 类型
          val c: Int  // 如果没有初始值类型不能省略
          c = 3       // 明确赋值
          sum(1,2)
          var e = 1
          var s1 = "a is $e"

          //字符串模板
          var s2 ="${s1.replace("is","was")} ,now is $e"



          return  ""
      }

    //
}