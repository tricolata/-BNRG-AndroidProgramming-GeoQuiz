package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Test

class QuizViewModelTest {

    @Test
    fun providesExpectedQuestionTest() {

        // given/Arrange
        val savedStateHandle = SavedStateHandle()
        // when/act
        val quizViewModel = QuizViewModel(savedStateHandle)
        // then/assert
        assertEquals(R.string.question_us, quizViewModel.currentQuestionText)
    }

    @Test
    fun wrapsAroundQuestionBank(){
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 3))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_us, quizViewModel.currentQuestionText)
    }
}