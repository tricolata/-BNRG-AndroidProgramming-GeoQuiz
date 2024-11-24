package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

//private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate(Bundle?) called")
        enableEdgeToEdge()
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
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }

        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }


    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)

        setEnabledAnswerButtons(true)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

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