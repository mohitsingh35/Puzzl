package com.ncs.guessr.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.content.edit

@SuppressLint("ServiceCast")
@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun correct(context: Context) {
    val allowVibrations = getAllowVibrations(context)

    if (allowVibrations) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val vibrationEffect4: VibrationEffect =
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            vibrator.cancel()
            vibrator.vibrate(vibrationEffect4)
        }
    }
}

@Composable
fun wrong(context: Context) {
    val allowVibrations = getAllowVibrations(context)

    if (allowVibrations) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val vibrationEffect5: VibrationEffect =
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK)
            vibrator.cancel()
            vibrator.vibrate(vibrationEffect5)
        }
    }
}

private const val PREFS_KEY = "com.ncs.guessr.settings"
private const val ALLOW_VIBRATIONS_KEY = "allow_vibrations"

private fun getSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
}

fun setAllowVibrations(context: Context, allow: Boolean) {
    val prefs = getSharedPreferences(context)
    prefs.edit {
        putBoolean(ALLOW_VIBRATIONS_KEY, allow)
        apply()
    }
}

fun getAllowVibrations(context: Context): Boolean {
    val prefs = getSharedPreferences(context)
    return prefs.getBoolean(ALLOW_VIBRATIONS_KEY, true)
}



