package com.example.level2_task2
 data class Question (
    var questionText: String,
    var answer: Boolean
) {
    companion object {
        val QUESTIONS = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grants 12 ECTS.",
            "A Unit in Kotlin corresponds to a void in Java.",
            "In Kotling 'when' replaces the 'switch' operator in Java."
        )
        val ANSWERS = arrayOf(
            false,
            false,
            true,
            true
        )
    }
}