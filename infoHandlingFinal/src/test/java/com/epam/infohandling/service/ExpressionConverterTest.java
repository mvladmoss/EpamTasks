package com.epam.infohandling.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ExpressionConverterTest {

    private ExpressionConverter converter = new ExpressionConverter();

    @Test(dataProvider = "dataForReplaceNumberTest")
    public void testReplaceNumberExpressionsByNumber(String actual, String string){
        //given
        //when
        String expected = converter.replaceExpressionsByNumber(string);
        //then
        assertEquals(actual, expected);
    }
    @DataProvider(name = "dataForReplaceNumberTest")
    public Object[][] dataForReplaceNumberTest(){
        return new Object[][]{
                {"erggerggt 52 eg egf egee ", "erggerggt [50&2&+] eg egf egee "},
                {"erggerggt 14 eg egf egee ", "erggerggt [8&2&7&4&+&*&-] eg egf egee "},
                {"erggerggt 2 eg egf egee ", "erggerggt [2&1&*] eg egf egee "},
        };
    }

}
