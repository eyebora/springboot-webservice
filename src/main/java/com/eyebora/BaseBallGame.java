package com.eyebora;

public class BaseBallGame {

    public static void main(String[] args){

        BaseBallGame bbg = new BaseBallGame();

        bbg.startGame();
    }

    private void startGame() {
    }

    public String getScore(String hidden, String guessed){

        int strike = 0;
        int ball = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            if (hidden.charAt(i) == guessed.charAt(i)) {
                strike++;
            } else {
                for (int j = 0; j < 3; j++) {
                    if (hidden.charAt(i) == guessed.charAt(j)) {
                        ball++;
                    }
                }
            }
        }

        if(strike == 0 && ball == 0 )
            return null;

        if (strike > 0) {
            result.append(strike).append("S");
        }
        if (ball > 0) {
            result.append(ball).append("B");
        }

        return result.toString();
    }
}
