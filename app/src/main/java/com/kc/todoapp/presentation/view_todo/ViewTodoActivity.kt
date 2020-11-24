package com.kc.todoapp.presentation.view_todo

import android.os.Bundle
import com.kc.todoapp.R
import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.util.Constants
import com.kc.todoapp.util.base.BaseActivity
import com.kc.todoapp.util.extensions.dialog.alertDialog
import com.kc.todoapp.util.extensions.makeGone
import com.kc.todoapp.util.extensions.showToastLong
import com.kc.todoapp.util.network.Resource
import com.kc.todoapp.util.network.ResourceState
import kotlinx.android.synthetic.main.activity_view_todo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewTodoActivity : BaseActivity() {
    var isUpdate = false
    private val viewTodoViewModel by viewModel<ViewTodoViewModel>()
    var todo: DTodo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_todo)
        viewTodoViewModel.addTodoLiveData.observe(this, { observeTodo(it) })
        init()
    }

    private fun init() {
        if (intent.hasExtra(Constants.EXTRA_TODO_OBJECT)) {
            todo = intent?.extras?.getParcelable<DTodo>(Constants.EXTRA_TODO_OBJECT)
            todo.let {
                et_user_id.setText(it?.userId.toString())
                et_todo_id.setText(it?.id.toString())
                et_todo_title.setText(it?.title.toString())
                tv_todo_status_value.text = if (it?.completed!!) "Completed" else "Not Completed"
            }
            isUpdate = true
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = "View Todo"
            }
        } else {
            et_user_id.makeGone()
            tv_user_id.makeGone()
            tv_todo_id.makeGone()
            et_todo_id.makeGone()
            tv_todo_status.makeGone()
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = "Add new Todo"
            }
        }
        btn_addpost.setOnClickListener {
            if (isUpdate) {
                viewTodoViewModel.updateTodo(
                    DTodo(
                        et_user_id.text.toString().toInt(),
                        et_todo_id.text.toString().toInt(),
                        et_todo_title.text.toString().trim(),
                        todo?.completed
                    )
                )
            } else {
                viewTodoViewModel.updateTodo(
                    DTodo(
                        1,
                        2,
                        et_todo_title.text.toString().trim(),
                        todo?.completed
                    )
                )
            }
        }
    }

    private fun observeTodo(resource: Resource<DTodo>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    if (isUpdate) {
                        showToastLong(getString(R.string.msg_item_updated))
                    } else {
                        showToastLong(getString(R.string.msg_item_added))
                    }
                    finish()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    alertDialog("Error", it.message) {
                        positiveButton("OK") {

                        }
                    }
                }
            }
        }
    }

    private fun observeUpdateTodo(resource: Resource<DTodo>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    showToastLong(getString(R.string.msg_item_updated))
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    alertDialog("Error", it.message) {
                        positiveButton("OK") {

                        }
                    }
                }
            }
        }
    }
}