package com.example.breakingmath

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.breakingmath.databinding.ActivityEasyLvlBinding

class MainActivity2 : AppCompatActivity() {
    private var  _binding: ActivityEasyLvlBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityEasyLvlBinding dosn't be null")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_lvl)
        var sp = getSharedPreferences("SP", Context.MODE_PRIVATE)
        sp.edit().putString("TY", "9").commit()
        var logout:Button = findViewById(R.id.btnlogout)
        logout.setOnClickListener{
            sp.edit().putString("TY", "-9").commit()
            Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

        _binding = ActivityEasyLvlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val trainer = MathTrain()
        showNextQuestion(trainer)

        with(binding){
            btnContinue.setOnClickListener {
                layoutResult.isVisible = false
                MarkNeutral(layoutAnswer1, tvVariant1, tvVariantVal1)
                MarkNeutral(layoutAnswer2, tvVariant2, tvVariantVal2)
                MarkNeutral(layoutAnswer3, tvVariant3, tvVariantVal3)
                MarkNeutral(layoutAnswer4, tvVariant4, tvVariantVal4)
                showNextQuestion(trainer)
            }
            btnSkip.setOnClickListener{
                showNextQuestion(trainer)
            }
        }
    }
    private fun showNextQuestion(trainer: MathTrain) {
        val firstQuestion: Question? = trainer.getNextQuestion()
        with(binding) {
            if (firstQuestion == null || firstQuestion.variants.size < NUMBER_OF_ANSWERS) {
                tvExample.isVisible = false
                layoutVariants.isVisible = false
                btnSkip.text = "Поздравляю, вы, прошли, игру"
            } else {
                btnSkip.isVisible = true
                tvExample.isVisible = true
                tvExample.text = firstQuestion.correctAnswer.example

                tvVariantVal1.text = firstQuestion.variants[0].answer
                tvVariantVal2.text = firstQuestion.variants[1].answer
                tvVariantVal3.text = firstQuestion.variants[2].answer
                tvVariantVal4.text = firstQuestion.variants[3].answer

                layoutAnswer1.setOnClickListener{
                    if (trainer.checkAnswer(0)) {
                        AnswerCorrect(layoutAnswer1, tvVariant1, tvVariantVal1)
                        ShowResultMessage(true)
                    } else{
                        AnswerWrong(layoutAnswer1, tvVariant1, tvVariantVal1)
                        ShowResultMessage(false)
                    }
                }
                layoutAnswer2.setOnClickListener{
                    if (trainer.checkAnswer(1)) {
                        AnswerCorrect(layoutAnswer2, tvVariant2, tvVariantVal2)
                        ShowResultMessage(true)
                    } else{
                        AnswerWrong(layoutAnswer2, tvVariant2, tvVariantVal2)
                        ShowResultMessage(false)
                    }
                }
                layoutAnswer3.setOnClickListener{
                    if (trainer.checkAnswer(2)) {
                        AnswerCorrect(layoutAnswer3, tvVariant3, tvVariantVal3)
                        ShowResultMessage(true)
                    } else{
                        AnswerWrong(layoutAnswer3, tvVariant3, tvVariantVal3)
                        ShowResultMessage(false)
                    }
                }
                layoutAnswer4.setOnClickListener{
                    if (trainer.checkAnswer(3)) {
                        AnswerCorrect(layoutAnswer4, tvVariant4, tvVariantVal4)
                        ShowResultMessage(true)
                    } else{
                        AnswerWrong(layoutAnswer4, tvVariant4, tvVariantVal4)
                        ShowResultMessage(false)
                    }
                }
            }
        }
    }

    private fun MarkNeutral(
        layoutAnswer: LinearLayout,
        tvVariantNum: TextView,
        tvVariantValue: TextView
    ){
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity2,
            R.drawable.shape_round_cont
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity2,
                R.color.black
            )
        )
        tvVariantNum.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity2,
                R.drawable.shape_round
            )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity2,
                    R.color.white
                )
            )
        }
    }


    private fun AnswerWrong(
        layoutAnswer: LinearLayout,
        tvVariantNum: TextView,
        tvVariantValue: TextView
    ){
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity2,
            R.drawable.shape_round_cont_wrong
        )

        tvVariantNum.background = ContextCompat.getDrawable(
            this@MainActivity2,
            R.drawable.shape_round_var_wrong
        )

        tvVariantNum.setTextColor(
            ContextCompat.getColor(
                this@MainActivity2,
                R.color.white
            )
        )
        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity2,
                R.color.wrong_answer
            )
        )
    }

    private fun AnswerCorrect(
        layoutAnswer: LinearLayout,
        tvVariantNum: TextView,
        tvVariantValue: TextView
    ){
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity2,
            R.drawable.shape_round_cont_correct
        )

        tvVariantNum.background = ContextCompat.getDrawable(
            this@MainActivity2,
            R.drawable.shape_round_var_correct
        )

        tvVariantNum.setTextColor(
            ContextCompat.getColor(
                this@MainActivity2,
                R.color.white
            )
        )
        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity2,
                R.color.correct_answer
            )
        )
    }

    private fun ShowResultMessage(isCorrect: Boolean){
        val color: Int
        val messageText: String
        val resultIcon: Int
        if (isCorrect){
            color = ContextCompat.getColor(this@MainActivity2, R.color.correct_answer)
            resultIcon = R.drawable.ic_like
            messageText = "Great!"
        } else {
            color = ContextCompat.getColor(this@MainActivity2, R.color.wrong_answer)
            resultIcon = R.drawable.ic_wrong
            messageText = "Incorrect!"
        }
        with(binding){
            btnSkip.isVisible = false
            layoutResult.isVisible = true
            btnContinue.setTextColor(color)
            layoutResult.setBackgroundColor(color)
            tvResultMessage.text = messageText
            ivResultIcon.setImageResource(resultIcon)
        }
    }
}













