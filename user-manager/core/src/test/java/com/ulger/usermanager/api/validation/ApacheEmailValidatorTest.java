package com.ulger.usermanager.api.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApacheEmailValidatorTest {

    private EmailValidator emailValidator = new ApacheEmailValidator();

    @Test
    public void test() {
        assert emailValidator.isValid("a-@hotmail.com");
        assert emailValidator.isValid("-@hotmail.com");
        assert emailValidator.isValid("1@hotmail.com");
        assert !emailValidator.isValid("a-@.com");
        assert !emailValidator.isValid("1");
        assert !emailValidator.isValid("a");
    }
}