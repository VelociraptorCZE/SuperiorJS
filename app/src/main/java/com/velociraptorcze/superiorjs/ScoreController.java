package com.velociraptorcze.superiorjs;

public class ScoreController {

    public Integer CheckResult(String solution, Integer score, String button) {
        return solution.contains(button) ? score + 1 : score;
    }
}