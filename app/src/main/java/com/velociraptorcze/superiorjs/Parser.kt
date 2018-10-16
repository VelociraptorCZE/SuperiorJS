/*
* SuperiorJS v1.0
* Copyright (C) Simon Raichl 2018
* MIT License
* Use this as you want, share it as you want, do basically whatever you want with this :)
*/

package com.velociraptorcze.superiorjs

import java.util.Random

open class Parser {

    open fun getQueryContent(input: String, questionsPicked: Array<Int>, questionsCache: Array<Int>): Array<String> {
        val allQuestions = getAllQuestions(input)
        var randomNum : Int
        do{
            randomNum = getRandomNumber(allQuestions)
        }
        while(questionsPicked.contains(randomNum) || questionsCache.contains(randomNum))
        val randomQuestion = allQuestions[randomNum].split("::")
        return arrayOf(randomQuestion[0], randomQuestion[1], randomNum.toString())
    }

    open fun getAllQuestions(input: String): List<String> {
        return input.split("\n")
    }

    private fun getRandomNumber(max: List<String>): Int {
        return Random().nextInt(max.size-1)
    }
}