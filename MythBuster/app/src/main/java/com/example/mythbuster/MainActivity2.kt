package com.example.mythbuster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {
    // 1. Reference the Data Class (Make sure this is defined in your project)
    data class Question(
        val text: String,
        val answer: Boolean
    )

    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var score = 0
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val txtQuiz = findViewById<TextView>(R.id.txtQuiz)
        val btnHack = findViewById<Button>(R.id.btnHack)
        val btnMyth = findViewById<Button>(R.id.btnMyth)
        val btnNext = findViewById<Button>(R.id.btnNext)

        // 2. Initialize the list with Question objects, not just Strings
        questions = listOf(
            Question("Drinking cold water burns significantly more calories", false),
            Question("Cracking your knuckles causes arthritis", false),
            Question("You must wait 24 hours before reporting a missing person", false),
            Question("Eating carrots improves night vision", false),
            Question("You lose most body heat through your head", false)
        )

        // 3. Define functions properly (they can stay inside or move outside onCreate)
        fun loadQuestion() {
            val q = questions[currentIndex]
            txtQuiz.text = q.text
            answered = false
        }

        fun checkAnswer(userAnswer: Boolean) {
            if (answered) return
            val currentQuestion = questions[currentIndex]

            if (userAnswer == currentQuestion.answer) {
                score++
            }
            answered = true
            // Optional: Provide feedback to user here (Toast or color change)
        }

        btnHack.setOnClickListener { checkAnswer(true) }
        btnMyth.setOnClickListener { checkAnswer(false) }

        btnNext.setOnClickListener {
            if (currentIndex < questions.size - 1) {
                currentIndex++
                loadQuestion()
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish() // Close quiz activity so user can't "go back" to it
            }
        }

        loadQuestion()
    }
}