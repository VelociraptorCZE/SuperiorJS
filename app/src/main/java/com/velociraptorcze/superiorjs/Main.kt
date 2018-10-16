package com.velociraptorcze.superiorjs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import java.lang.Float.parseFloat
import java.lang.Integer.parseInt

class Main : AppCompatActivity() {

    private var solution = ""
    private var score = 0
    private var totalQuestions = 0
    private var allQuestionsString = ""
    private var allQuestionsNum = 0
    private var questionsPicked = arrayOf<Int>()
    private var questionsCache = arrayOf<Int>()
    private var buttons = arrayOf(R.id.trueBtn, R.id.falseBtn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        init()
        appendEventListeners()
        newQuery()
    }

    private fun init(){
        applicationContext.assets.open("questions").bufferedReader().use {
            allQuestionsString = it.readText()
        }
        allQuestionsNum = Parser().getAllQuestions(allQuestionsString).size
    }

    private fun appendEventListeners(){
        for (button: Int in buttons){
            findViewById<Button>(button).setOnClickListener {
                score = ScoreController().CheckResult(solution, score, findViewById<Button>(button).text.toString())
                totalQuestions++
                refreshScore()
                newQuery()
            }
        }
    }

    private fun newQuery(){
        checkQuestions()
        refreshScore()
        if (questionsPicked.size < 10){
            val content = Parser().getQueryContent(allQuestionsString, questionsPicked, questionsCache)
            findViewById<TextView>(R.id.questionText).text = content[0]
            setTextSize(content[0])
            solution = content[1]
            questionsPicked += parseInt(content[2])
            questionsCache += parseInt(content[2])
        }
        else{
            AlertDialog.Builder(this)
                    .setMessage("Game over! Your score is $score/$totalQuestions!").setPositiveButton("OK", null)
                    .create().show()
            restartGame()
        }
    }

    private fun restartGame() {
        score = 0
        totalQuestions = 0
        questionsPicked = arrayOf()
        newQuery()
    }

    private fun checkQuestions() {
        if (questionsCache.size == allQuestionsNum-3){
            questionsCache = arrayOf()
        }
    }

    private fun refreshScore() {
        findViewById<TextView>(R.id.scoreText).text = ("Score: $score/$totalQuestions")
    }

    private fun setTextSize(input: String){
        findViewById<TextView>(R.id.questionText).textSize = parseFloat((30 - (input.length * 0.25)).toString())
    }
}