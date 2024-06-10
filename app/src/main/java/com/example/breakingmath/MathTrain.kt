package com.example.breakingmath

data class Numbers(
    val example: String,
    val answer: String,
    var learned: Boolean = false,
)

data class Question(
    val variants: List<Numbers>,
    val correctAnswer: Numbers,
)

class MathTrain {

    private val dictionary = listOf(
        Numbers("5 + 3 = ?", "8"),
        Numbers("15 - 4 = ?", "11"),
        Numbers("7 * 2 = ?", "14"),
        Numbers("8 / 2 = ?", "4"),
        Numbers("12 + 6 = ?", "18"),
        Numbers("24 - 7 = ?", "17"),
        Numbers("3 * 4 = ?", "12"),
        Numbers("10 / 5 = ?", "2"),
        Numbers("4 - 1 = ?", "3"),
        Numbers("14 - 5 = ?", "9"),
        Numbers("5 * 3 = ?", "15"),
        Numbers("13 * 4 = ?", "52"),
        Numbers("6 + 7 = ?", "13"),
        Numbers("9 - 3 = ?", "6"),
        Numbers("2 * 5 = ?", "10"),
        Numbers("15 - 8 = ?", "7"),
        Numbers("3 * 7 = ?", "21"),
        Numbers("14 / 7 = ?", "2"),
        Numbers("8 + 8 = ?", "16"),
        Numbers("10 - 9 = ?", "1"),
        Numbers("5 * 4 = ?", "20"),
        Numbers("20 / 4 = ?", "5"),
        Numbers("14 + 9 = ?", "23"),
        Numbers("11 + 8 = ?", "19"),
        Numbers("4 * 6 = ?", "24"),
        Numbers("18 / 4 = ?", "72"),
        Numbers("4 * 9 = ?", "36"),
        Numbers("11 * 3 = ?", "33"),
        Numbers("68 / 2 = ?", "34"),
        Numbers("9 * 3 = ?", "27"),
        Numbers("78 / 3 = ?", "26"),
        Numbers("14 + 45 = ?", "59"),
        Numbers("72 + 5 = ?", "77"),
        Numbers("12 * 4 = ?", "48"),
        Numbers("29 + 5 = ?", "34"),
        Numbers("94 - 44 = ?", "50"),
        Numbers("16 * 4", "64"),
        Numbers("5 * 5 = ?", "25"),
        Numbers("60 / 2 = ?", "30"),
        Numbers("5 * 9 = ?", "45"),
        Numbers("84 - 15 = ?", "69"),

    )

    private var currentQuestion: Question? = null

    fun getNextQuestion(): Question? {

        val notLearnedList = dictionary.filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val questionWords =
            if (notLearnedList.size < NUMBER_OF_ANSWERS) {
                val learnedList = dictionary.filter { it.learned }.shuffled()
                notLearnedList.shuffled()
                    .take(NUMBER_OF_ANSWERS) + learnedList
                    .take(NUMBER_OF_ANSWERS - notLearnedList.size)
            } else {
                notLearnedList.shuffled().take(NUMBER_OF_ANSWERS)
            }.shuffled()

        val correctAnswer: Numbers = questionWords.random()

        currentQuestion = Question(
            variants = questionWords,
            correctAnswer = correctAnswer,
        )
        return currentQuestion
    }

    fun checkAnswer(userAnswerIndex: Int?): Boolean {

        return currentQuestion?.let {

            val correctAnswerId = it.variants.indexOf(it.correctAnswer)
            if (correctAnswerId == userAnswerIndex) {
                it.correctAnswer.learned = true
                true
            } else {
                false
            }
        } ?: false
    }
}

const val NUMBER_OF_ANSWERS: Int = 4