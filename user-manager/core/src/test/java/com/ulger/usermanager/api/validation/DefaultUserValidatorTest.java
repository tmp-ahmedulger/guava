package com.ulger.usermanager.api.validation;

import com.ulger.usermanager.api.UserModificationData;
import com.ulger.validation.ValidationResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserValidatorTest {

    @Mock
    private EmailValidator EmailValidator;

    @InjectMocks
    private DefaultUserValidator defaultCustomerValidator;

    @Test
    public void test_validation_by_null_data() {

        Mockito
                .when(EmailValidator.isValid(anyString()))
                .thenReturn(true);

        ValidationResult validationResult = defaultCustomerValidator.validateToCreate(null);

        assert validationResult.hasError();
        assert validationResult.getErrors().size() == 1;
        assert validationResult.getErrors().contains("Creation data must not blank");
    }

    @Test
    public void test_validation_by_blank_inputs() {

        Mockito
                .when(EmailValidator.isValid(anyString()))
                .thenReturn(true);

        UserModificationData userModificationData = new UserModificationData();
        userModificationData.setEmail("");
        userModificationData.setDisplayName("");
        userModificationData.setRawPassword("");

        ValidationResult validationResult = defaultCustomerValidator.validateToCreate(userModificationData);

        assert validationResult.hasError();
        assert validationResult.getErrors().size() == 3;
        assert validationResult.getErrors().containsAll(Arrays.asList("Display name is required", "Email is required", "Password is required"));
    }

    @Test
    public void test_validation_by_invalid_inputs() {

        Mockito
                .when(EmailValidator.isValid(anyString()))
                .thenReturn(false);

        UserModificationData userModificationData = new UserModificationData();
        userModificationData.setEmail("a");
        userModificationData.setDisplayName("Ahmet");
        userModificationData.setRawPassword("123");

        ValidationResult validationResult = defaultCustomerValidator.validateToCreate(userModificationData);

        assert validationResult.hasError();
        assert validationResult.getErrors().size() == 1;
        assert validationResult.getErrors().contains("Given email is not valid");
    }
}