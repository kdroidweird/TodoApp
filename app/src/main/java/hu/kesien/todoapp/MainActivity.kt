package hu.kesien.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoList(mutableListOf())

        val recyclerView = findViewById<RecyclerView>(R.id.rvTodoList)
        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btAddTodo).setOnClickListener {
            val editText = findViewById<EditText>(R.id.etTodoTitle)
            val todoTitle = editText.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                editText.text.clear()
            }
        }

        findViewById<Button>(R.id.btDeleteTodo).setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}