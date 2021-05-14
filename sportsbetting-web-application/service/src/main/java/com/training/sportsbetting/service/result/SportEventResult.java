package com.training.sportsbetting.service.result;

public class SportEventResult {

    private String finalScore;
    private String winner;

    public SportEventResult(String finalScore, String winner) {
        super();
        this.finalScore = finalScore;
        this.winner = winner;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public String getWinner() {
        return winner;
    }

}
