package com.example.my_task_manager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var taskTitle: EditText
    private lateinit var taskDescription: EditText
    private lateinit var cancelButton: Button
    private lateinit var saveButton: Button
    private lateinit var dateRangeTextView: TextView

    private var startDate: String = ""
    private var endDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        calendarView = findViewById(R.id.calendarView)
        taskTitle = findViewById(R.id.taskTitle)
        taskDescription = findViewById(R.id.taskDescription)
        cancelButton = findViewById(R.id.cancelButton)
        saveButton = findViewById(R.id.saveButton)
        dateRangeTextView = findViewById(R.id.dateRangeTextView)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            if (startDate.isEmpty()) {
                startDate = date
                Toast.makeText(this, "Date de début : $startDate", Toast.LENGTH_SHORT).show()
            } else {
                endDate = date
                dateRangeTextView.text = "Tâche de $startDate à $endDate"
                Toast.makeText(this, "Date de fin : $endDate", Toast.LENGTH_SHORT).show()
            }
        }

        cancelButton.setOnClickListener {
            resetFields()
            Toast.makeText(this, "Tâche annulée", Toast.LENGTH_SHORT).show()
        }

        saveButton.setOnClickListener {
            val title = taskTitle.text.toString()
            val description = taskDescription.text.toString()

            if (title.isEmpty() || startDate.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir le titre et la date de début", Toast.LENGTH_SHORT).show()
            } else {
                val dateRange = if (endDate.isEmpty()) startDate else "$startDate - $endDate"
                val intent = Intent(this, MainActivity3::class.java)
                intent.putExtra("TASK_TITLE", title)
                intent.putExtra("TASK_DESCRIPTION", description)
                intent.putExtra("TASK_DATE", dateRange)
                startActivity(intent)
            }
        }
    }

    private fun resetFields() {
        taskTitle.text.clear()
        taskDescription.text.clear()
        startDate = ""
        endDate = ""
        dateRangeTextView.text = "Tâche de --"
    }
}
