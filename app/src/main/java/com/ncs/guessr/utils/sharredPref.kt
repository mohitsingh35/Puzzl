package com.ncs.guessr.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

private const val PREFS_NAME = "Guessr_Prefs"
private const val KEY_TEXT_VALUE = "text_value_key"

fun saveTextValue(context: Context, value: Int) {
    val sharedPref = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putInt(KEY_TEXT_VALUE, value)
    editor.apply()
}

fun getTextValue(context: Context): Int {
    val sharedPref = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    return sharedPref.getInt(KEY_TEXT_VALUE, 5) ?: 3
}