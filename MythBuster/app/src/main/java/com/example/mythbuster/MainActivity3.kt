package com.example.mythbuster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val txtFeedback = findViewById<TextView>(R.id.txtFeedback)
        val btnReview = findViewById<Button>(R.id.btnReview)

        // 1. Retrieve data from the Intent
        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        // 2. Display the results
        txtTotal.text = "Score: $score / $total"

        txtFeedback.text = when {
            score == total -> "Perfect! You're a Myth Master!"
            score >= 3 -> "Great job! Most myths busted."
            else -> "Keep learning! The internet is full of myths."
        }

        // 3. Fix: Change Intent target to MainActivity to restart the quiz
        btnReview.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Clear the activity stack so the user starts fresh
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}