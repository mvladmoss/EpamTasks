package com.epam.geometry.validators;

import com.epam.geometry.validator.DataValidator;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataValidatorTest {

    @DataProvider(name = "dataForValidation")
    public Object[][] dataForValidation() {
        return new Object[][]{
                {true, "1 2 -2,9 2 -2,5 8.93 -2,5 4.31 4.532"},
                {false, "1 2 -2,9 2 -2,5 d.93 -2,5 4.31 4.532"},
                {true, "0.577 0 1.634,0 -1 0,1.732 0 0,0 1 0"},
                {false, "0.577 0 qwe,0 -1 0,1.732 0 0,0 1 0"},
                {false, " 0 1.634,0 -1 0,1.732 0 0,0 1 0"},
                {true,"0.77 6 1.34,0 -6 0,1.732 0 3,0 2 1"},
                {false,"0.77 6 1.34,0 -6 0,1.732 0 3,0 2.5 1 1"}
        };
    }

    @Test(dataProvider = "dataForValidation")
    public void shouldValidateString(boolean expectedValidate,String toValidate){
        //given
        DataValidator validator = new DataValidator();
        //when
        boolean actualValidate = validator.validate(toValidate);
        //then
        Assertions.assertEquals(expectedValidate,actualValidate);
    }
}
