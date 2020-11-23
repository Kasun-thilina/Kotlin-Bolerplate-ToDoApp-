package com.kc.todoapp.util.base

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kc.todoapp.R

/**
 * Created By Kasun Thilina 29/02/2020
 * This abstract class for Activity
 */
abstract class BaseActivity : AppCompatActivity() {
    private var progress: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progress = Dialog(this, R.style.ProgressbarStyle).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.item_progress)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }

//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor =
//            ContextCompat.getColor(
//                this,
//                R.color.colorAccent
//            )

    }

    fun setStatusBarColor(color: Int) {
        window.statusBarColor =
            ContextCompat.getColor(
                this,
                color
            )
    }
    fun showProgress() {
        progress?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    fun hideProgress() {
        progress?.let {
            if (it.isShowing) {
                progress?.dismiss()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}