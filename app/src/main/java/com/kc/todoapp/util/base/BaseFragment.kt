package com.kc.todoapp.util.base

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Created By Kasun Thilina 29/02/2020
 * This abstract class for Fragments
 *
 */
abstract class BaseFragment : Fragment() {

    private var parentActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            // activity?.onFragmentAttached()
        }
    }

    fun getBaseActivity() = parentActivity

    fun showProgress() {
        getBaseActivity()?.showProgress()
    }

    fun hideProgress() {
        getBaseActivity()?.hideProgress()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideProgress()
    }
}