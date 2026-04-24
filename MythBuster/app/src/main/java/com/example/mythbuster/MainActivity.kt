package com.example.mythbuster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// 1. Move the data class outside the listener so it's accessible everywhere
data class Question(
    val text: String,
    val answer: Boolean,
    val explanation: String
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ensure R.id.btnStart exists in your activity_main.xml file
        val btnStart = findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener {
            // 2. Start the activity
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        // Handle system bars padding
        val mainView = findViewById<android.view.View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}