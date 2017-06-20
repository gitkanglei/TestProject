package com.pptv.mylistviewadapter.kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @anthor LeiKang
 */
class MyView : View {

        // 构造函数
        constructor(ctx: Context?) : this(ctx,null)

        constructor(ctx: Context?, attrs: AttributeSet?) : super(ctx, attrs)
}