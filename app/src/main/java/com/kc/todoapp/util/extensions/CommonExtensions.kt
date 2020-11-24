package com.kc.todoapp.util.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat

/**
 * Show Long Toast message from string resource
 * */
fun Context?.showToastLong(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) {
    this?.let {
        Toast.makeText(it, textId, duration).show()
    }

}

/**
 * Show Long Toast message from only String
 * */
fun Context?.showToastLong(text: String, duration: Int = Toast.LENGTH_LONG) {
    this?.let {
        Toast.makeText(it, text, duration).show()
    }
}

/**
 * Start Activity from context
 * */
inline fun <reified T : Activity> Context?.startActivity(func: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java).apply {
        func()
    }
    this?.startActivity(intent)
}

/**
 * Hide Keyboard
 */
fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus ?: View(this)
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    window.decorView
}

/**
 * Permission Check
 * */
fun Activity.isPermissionGranted(vararg permissions: String): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        permissions.all { permission ->
            applicationContext.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        }
    } else true
}

fun Activity.requestPermission(permissions: Array<String>, REQUEST_CODE: Int) {
    ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
}

//Location Provider check

fun Activity.isProviderEnabled(): Boolean {
    val manager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

