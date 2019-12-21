package me.ham;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;

public class Test {

//    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetition}")
    @RepeatedTest(10)
    @DisplayName("반복 테스트")
    void repeatTest(RepetitionInfo repetitionInfo){
        System.out.println(repetitionInfo.getCurrentRepetition());
    }

    @ParameterizedTest(name = "{index},{displayName}")
    @ValueSource(strings = {"하나","둘","삼","넷"})
    @DisplayName("파라미터 여러개 테스트")
    void parameteizedTest(String messgage){
        System.out.println(messgage);
    }
}
