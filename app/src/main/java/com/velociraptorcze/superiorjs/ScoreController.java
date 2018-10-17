/*
 * SuperiorJS v1.1
 * Copyright (C) Simon Raichl 2018
 * MIT License
 * Use this as you want, share it as you want, do basically whatever you want with this :)
 */

package com.velociraptorcze.superiorjs;

public class ScoreController {

    public Integer CheckResult(String solution, Integer score, String button) {
        return solution.contains(button) ? score + 1 : score;
    }
}