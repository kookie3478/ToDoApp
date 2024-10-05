package com.example.todoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        todoAdapter = ToDoAdapter(mutableListOf())
        binding.rvToDoItems.adapter = todoAdapter
        binding.rvToDoItems.layoutManager = LinearLayoutManager(this)

        // Add Todo Items
        binding.btnAddToDo.setOnClickListener {
            val todoTitle = binding.etToDoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = ToDo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etToDoTitle.text.clear() // Clear input
            }
        }

        // Delete completed todos
        binding.btnDeleteDoneToDo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}
