package com.dullabs.notiga.utils

import android.content.Context


fun dpToPx(dp: Int, context: Context): Int {
    return (dp * context.resources.displayMetrics.density).toInt()
}
fun pxToDp(px: Int, context: Context): Int {
    return (px / context.resources.displayMetrics.density).toInt()
}
