package com.example.todoapp


import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding


class ToDoAdapter(
    private val todos: MutableList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){

    class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ToDoViewHolder(binding)
    }

    fun addTodo(todo: ToDo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvToDoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curTodo = todos[position]

        holder.binding.apply {
            tvToDoTitle.text = curTodo.item
            cbDoneItem.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvToDoTitle, curTodo.isChecked)
            cbDoneItem.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvToDoTitle, isChecked)
                curTodo.isChecked = isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
