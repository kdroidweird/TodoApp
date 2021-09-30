package hu.kesien.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoList(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoList.TodoItemViewHolder>() {


    class TodoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_item,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isComplete
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeTrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            val textView: TextView = findViewById(R.id.tvTodoTitle)
            val checkBox: CheckBox = findViewById(R.id.ckIsComplete)
            textView.text = curTodo.title
            checkBox.isChecked = curTodo.isComplete
            toggleStrikeTrough(textView, curTodo.isComplete)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeTrough(textView, isChecked)
                curTodo.isComplete = !curTodo.isComplete
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
