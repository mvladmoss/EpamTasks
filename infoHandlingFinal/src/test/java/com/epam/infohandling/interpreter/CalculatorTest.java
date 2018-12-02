package com.epam.infohandling.interpreter;

import com.epam.infohandling.interpreter.calculator.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test(dataProvider = "dataCalculationExpressions")
    public void shouldCalculateExpression(int actualCalculatedExpression, String stringExpression){
        //given
        //when
        Number expectedCalculatedExpression = calculator.calculate(stringExpression);
        //then
        assertEquals(actualCalculatedExpression,expectedCalculatedExpression);
    }

    @DataProvider(name = "dataCalculationExpressions")
    public Object[][] dataForSortingByNumOfSentences(){
        return new Object[][]{
                {52, "50&2&+"},
                {14, "8&2&7&4&+&*&-"},
                {2, "2&1&*"}
        };
    }
}
