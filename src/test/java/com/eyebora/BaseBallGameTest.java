package com.eyebora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseBallGameTest {


    @Test
    @DisplayName("0스트라이크 0볼")
    public void test_case1(){

        //given
        String hidden = "123";
        String guessed = "456";
        String expected = null;

        //when
        BaseBallGame baseBallGame = new BaseBallGame();
        String actual = baseBallGame.getScore(hidden,guessed);

        //then
        assertThat( actual ).isEqualTo( expected );
    }

    @Test
    @DisplayName("1스트라이크 0볼")
    public void test_case2(){

        //given
        String hidden = "123";
        String guessed = "156";
        String expected = "1S";

        //when
        BaseBallGame baseBallGame = new BaseBallGame();
        String actual = baseBallGame.getScore(hidden,guessed);

        //then
        assertThat( actual ).isEqualTo( expected );
    }



}
