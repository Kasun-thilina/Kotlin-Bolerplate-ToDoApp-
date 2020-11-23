package com.kc.todoapp.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kc.todoapp.R
import com.kc.todoapp.di.injectFeature
import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.util.base.BaseActivity
import com.kc.todoapp.util.extensions.dialog.alertDialog
import com.kc.todoapp.util.extensions.getDaySuffix
import com.kc.todoapp.util.network.Resource
import com.kc.todoapp.util.network.ResourceState
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
@SuppressLint("SetTextI18n")
class HomeActivity : BaseActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        injectFeature()
        homeViewModel.todoListLiveData.observe(this, { observeGetTodoList(it) })
        init()
    }

    private fun init() {
        todoListAdapter= TodoListAdapter(this, mutableListOf())
        mLayoutManager= LinearLayoutManager(this)

        rv_todo_list.layoutManager=mLayoutManager
        rv_todo_list.adapter=todoListAdapter

//        val current = LocalDateTime.now()
        val currentDate = Calendar.getInstance().time
        val date = SimpleDateFormat("EEEE, dd", Locale.getDefault())
        val onlyDay = SimpleDateFormat("dd", Locale.getDefault())
        tv_date.text="${date.format(currentDate)}${onlyDay.format(currentDate).toInt().getDaySuffix()}"
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getTodoList()
    }

    private fun observeGetTodoList(resource: Resource<List<DTodo>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    todoListAdapter.todoList.clear()
                    it.data?.let { todoList ->
                        todoListAdapter.todoList.addAll(todoList)
                    }
                    todoListAdapter.notifyDataSetChanged()
                    tv_task_count.text=getString(R.string.text_task_count,todoListAdapter.itemCount.toString())
                    hideProgress()
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