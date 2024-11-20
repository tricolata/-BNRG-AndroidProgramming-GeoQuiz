package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

//private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_us, false),
        Question(R.string.question_ocean, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate(Bundle?) called")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setTitle(R.string.app_name)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener{
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.prevButton.setOnClickListener {
            val temp = if (currentIndex - 1 < 0) currentIndex - 1 + questionBank.size else currentIndex - 1
            currentIndex = temp % questionBank.size
            updateQuestion()
        }

        // challenge chapter 2.1
        binding.questionTextView.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
//        updateQuestion()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById()) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }


    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)

        setEnabledAnswerButtons(true)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        setEnabledAnswerButtons(false)
    }

    private fun setEnabledAnswerButtons(isEnabled: Boolean) {
        binding.trueButton.isEnabled = isEnabled
        binding.falseButton.isEnabled = isEnabled
    }

    // Logging functions

//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart() called")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume() called")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause() called")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop() called")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy() called")
//    }
}


/*
ToDo :: Show score when finished quiz
ToDo :: Answering is disabled when returning to a question
 */