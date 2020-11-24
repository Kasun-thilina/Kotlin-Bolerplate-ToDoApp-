package com.kc.todoapp.presentation.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kc.todoapp.R
import com.kc.todoapp.di.injectFeature
import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.presentation.view_todo.ViewTodoActivity
import com.kc.todoapp.util.adapter.SwipeToDeleteCallback
import com.kc.todoapp.util.base.BaseActivity
import com.kc.todoapp.util.extensions.dialog.alertDialog
import com.kc.todoapp.util.extensions.getDaySuffix
import com.kc.todoapp.util.extensions.showToastLong
import com.kc.todoapp.util.extensions.startActivity
import com.kc.todoapp.util.network.Resource
import com.kc.todoapp.util.network.ResourceState
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

@SuppressLint("SetTextI18n")
class HomeActivity : BaseActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private var isFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        injectFeature()
        homeViewModel.todoListLiveData.observe(this, { observeGetTodoList(it) })
        homeViewModel.deleteTodoLiveData.observe(this, { observeDeleteTodo(it) })
        homeViewModel.updateTodoLiveData.observe(this, { observeUpdateTodo(it) })
        init()
        initListeners()
    }

    private fun initListeners() {
        pull_to_refresh.setOnRefreshListener {
            homeViewModel.getTodoList()
        }
        fab.setOnClickListener {
            this.startActivity<ViewTodoActivity> {}
        }
    }

    private fun init() {
        todoListAdapter = TodoListAdapter(this, mutableListOf()) {
            homeViewModel.updateTodo(it)
        }
        mLayoutManager = LinearLayoutManager(this)

        rv_todo_list.layoutManager = mLayoutManager
        rv_todo_list.adapter = todoListAdapter
        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val tempTodoItem = todoListAdapter.todoList[position]
                var isUndo = false
                todoListAdapter.todoList.removeAt(position)
                todoListAdapter.notifyDataSetChanged()
                val snackbar = Snackbar.make(
                    cl_parent,
                    getString(R.string.msg_item_removed),
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction(
                    getString(R.string.action_undo)
                ) {
                    todoListAdapter.todoList.add(position, tempTodoItem)
                    todoListAdapter.notifyDataSetChanged()
                    isUndo = true
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
                //2750
                Timer().schedule(2800) {
                    if (!isUndo) {
                        homeViewModel.deleteTodo(tempTodoItem.id!!)
                    }
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rv_todo_list)

//        val current = LocalDateTime.now()
        val currentDate = Calendar.getInstance().time
        val dateFormatter = SimpleDateFormat("EEEE, dd", Locale.getDefault())
        val onlyDayFormatter = SimpleDateFormat("dd", Locale.getDefault())
        val monthFormatter = SimpleDateFormat("MMMM", Locale.getDefault())
        tv_date.text = "${dateFormatter.format(currentDate)}${
            onlyDayFormatter.format(currentDate).toInt().getDaySuffix()
        }"
        tv_month.text = monthFormatter.format(currentDate)

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
                    pull_to_refresh.isRefreshing = false
                    todoListAdapter.todoList.clear()
                    todoListAdapter.notifyDataSetChanged()
                    it.data?.let { todoList ->
                        todoListAdapter.todoList.addAll(todoList)
                    }
                    todoListAdapter.notifyDataSetChanged()
                    tv_task_count.text =
                        getString(R.string.text_task_count, todoListAdapter.itemCount.toString())
                    if (isFirstTime) {
                        Snackbar.make(
                            cl_parent,
                            getString(R.string.msg_swipe_left),
                            Snackbar.LENGTH_LONG
                        ).show()
                        isFirstTime = false
                    }
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

    private fun observeDeleteTodo(resource: Resource<DTodo>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    showToastLong(getString(R.string.msg_item_deleted))
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