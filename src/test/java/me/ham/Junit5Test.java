package me.ham;

import me.ham.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Junit5Test {

//    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetition}")
    @DisplayName("반복 테스트")
    @RepeatedTest(10)
    void repeatTest(RepetitionInfo repetitionInfo){
        System.out.println(repetitionInfo.getCurrentRepetition());
    }

    @DisplayName("파라미터 여러개 테스트")
    @ParameterizedTest(name = "{index},{displayName}")
    @ValueSource(strings = {"하나","둘","삼","넷"})
    void parameteizedTest(String message){
        System.out.println(message);
    }

    @DisplayName("파라미터 컨버팅 테스트")
    @ParameterizedTest(name = "{index},{displayName}")
    @ValueSource(strings = {"하나","둘","삼","넷"})
    void parameteizedConvertingTest(@ConvertWith(UserConverter.class) User user){
        System.out.println(user.getUsername());
    }

    static class UserConverter extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object o, Class<?> targetClass) throws ArgumentConversionException {
            assertEquals(User.class, targetClass);
            User user = new User();
            user.setUsername(o.toString());
            return user;
        }
    }

    @DisplayName("파라미터 Aggregation 테스트")
    @ParameterizedTest(name = "{index},{displayName}")
    @CsvSource({"'hhs9102','password'", "'username', 'password'"})
    void parameteizedAggregationTest(@AggregateWith(UserAggregation.class)  User user){
        assertNotNull(user.getUsername());
        System.out.println(user.getUsername());
        assertNotNull(user.getPassword());
        System.out.println(user.getPassword());
    }

    static class UserAggregation implements ArgumentsAggregator{
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            User user = new User();
            user.setUsername(argumentsAccessor.getString(0));
            user.setPassword(argumentsAccessor.getString(1));
            return user;
        }
    }
}
