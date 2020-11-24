package com.kc.todoapp.presentation.home

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kc.todoapp.R
import com.kc.todoapp.domain.model.DTodo
import com.kc.todoapp.presentation.view_todo.ViewTodoActivity
import com.kc.todoapp.util.Constants.EXTRA_TODO_OBJECT
import com.kc.todoapp.util.adapter.Animate
import com.kc.todoapp.util.extensions.startActivity
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoListAdapter(
    var mContext: Context,
    var todoList: MutableList<DTodo>,
    val onUpdateField: (todo: DTodo) -> Unit
) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {
    private val animate = Animate()
    private var shouldAnimate = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            onBind(position)
            animate.setAnimation(shouldAnimate, itemView, position)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(position: Int) {
            val todoItem = todoList[position]
            val shortenText = if (todoItem.title!!.length >= 35) todoItem.title.take(20).trim()
                .plus("...") else todoItem.title
            setText(todoItem.completed!!, itemView, shortenText)
            itemView.tv_todo_date.text = "7.00 AM"
            itemView.setOnClickListener {
                mContext.startActivity<ViewTodoActivity> {
                    putExtra(EXTRA_TODO_OBJECT, todoItem)
                }
            }
            itemView.chk_todo.apply {
                setOnClickListener {
                    setText(isChecked, itemView, shortenText)
                    todoItem.completed = isChecked
                    onUpdateField(todoItem)
                }

            }
        }
    }

    private fun setText(status: Boolean, itemView: View, shortenText: String) {
        if (status) {
            itemView.tv_todo_title.apply {
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                text = shortenText
            }
            itemView.chk_todo.isChecked = true
        } else {
            itemView.tv_todo_title.apply {
                paintFlags = 0
                text = shortenText
            }
            itemView.chk_todo.isChecked = false
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                shouldAnimate = false
            }
        })
    }
}