package com.example.my_task_manager

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity3 : AppCompatActivity() {
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var addTaskButton: FloatingActionButton

    private val tasks = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        taskRecyclerView = findViewById(R.id.taskRecyclerView)
        addTaskButton = findViewById(R.id.addTaskButton)


        val newTitle = intent.getStringExtra("TASK_TITLE")
        val newDescription = intent.getStringExtra("TASK_DESCRIPTION")
        val newDate = intent.getStringExtra("TASK_DATE")

        if (newTitle != null && newDescription != null && newDate != null) {
            val newTask = Task(newTitle, newDescription, newDate, false)
            tasks.add(newTask)
        }

        taskAdapter = TaskAdapter(
            taskList = tasks,
            onTaskModified = { task ->
                val taskIndex = tasks.indexOf(task)
                if (taskIndex != -1) {
                    tasks[taskIndex] = task.copy(title = "Modifié : ${task.title}")
                    taskAdapter.notifyItemChanged(taskIndex)
                }
            },
            onTaskDeleted = { task ->
                val taskIndex = tasks.indexOf(task)
                if (taskIndex != -1) {
                    tasks.removeAt(taskIndex)
                    taskAdapter.notifyItemRemoved(taskIndex)
                    Toast.makeText(this, "Supprimé : ${task.title}", Toast.LENGTH_SHORT).show()
                }
            }
        )

        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = taskAdapter

        addTaskButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }


    }
}
