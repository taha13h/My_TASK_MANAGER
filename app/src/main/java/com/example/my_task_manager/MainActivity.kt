package com.example.my_task_manager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add)

        fabAdd.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)


        }
    }}
