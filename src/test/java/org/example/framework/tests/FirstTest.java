package org.example.framework.tests;


import org.example.framework.basetestclass.BaseTests;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FirstTest extends BaseTests {

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of("300 000", "6 месяцев", "50 000", "5.75%", "562 243,26"),
                Arguments.of("500 000", "3 месяца", "60 000", "4.25%", "625 953,18")
        );
    }


    @ParameterizedTest
    @MethodSource("data")
    public void startTest(String contributionAmount, String period, String replenish, String rate, String result) {

        app.getHomePage()
                .selectMenu("Вклады")
                .selectCurrency("Рубли")
                .fillField("Сумма вклада", contributionAmount)
                .selectPeriod(period)
                .fillField("Ежемесячное пополнение", replenish)
                .clickCheckboxByName("Ежемесячная капитализация")
                .checkRate(rate)
                .checkResult(result);
    }
}
