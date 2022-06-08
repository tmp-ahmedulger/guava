package com.ulger.usermanager.api;

import com.ulger.usermanager.api.data.UserDao;
import com.ulger.usermanager.api.validation.UserValidator;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserManagerTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserValidator userValidator;

    @Mock
    private CredentialEncoder credentialEncoder;

    @InjectMocks
    private DefaultUserManager customerManager;

    @Test
    public void test_getCustomerByEmail_with_blank_email() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerManager.getCustomerByEmail(""));

        assert exception.getMessage().equals("Email is required");
    }

    @Test
    public void test_getCustomerByEmail_with_empty_data() {

        Mockito
                .when(userDao.findByEmail(eq("emailX")))
                .thenReturn(Optional.of(createSimpleCustomer()));

        Optional<User> customer = customerManager.getCustomerByEmail("emailX");

        assert customer.isPresent();
        assert customer.get().getId() == null;
        assert customer.get().getEmail().equals("emailX");
        assert customer.get().getDisplayName().equals("displayNameX");
        assert customer.get().getCredential().equals("credentialX");
    }

    @Test
    public void test_add_customer_with_invalid_data() {

        ValidationResult validationResult = new ValidationResult();
        validationResult.addError("errorX");

        Mockito
                .when(userValidator.validateToCreate(anyObject()))
                .thenReturn(validationResult);

        ValidationException validationException = assertThrows(
                ValidationException.class,
                () -> customerManager.add(new UserModificationData()));

        assert validationException.getErrors().size() == 1;
        assert validationException.getErrors().contains("errorX");
    }

    @Test
    public void test_add_customer_with_valid_data_already_exists() {

        ValidationResult validationResult = new ValidationResult();

        Mockito
                .when(userValidator.validateToCreate(anyObject()))
                .thenReturn(validationResult);

        Mockito
                .when(userDao.findByEmail("emailX"))
                .thenReturn(Optional.of(createSimpleCustomer()));

        UserModificationData userModificationData = new UserModificationData();
        userModificationData.setEmail("emailX");

        assertThrows(
                UserAlreadyExistException.class,
                () -> customerManager.add(userModificationData));
    }

    @Test
    public void test_add_customer_with_valid_data_not_exists() {

        ValidationResult validationResult = new ValidationResult();

        Mockito
                .when(userValidator.validateToCreate(anyObject()))
                .thenReturn(validationResult);

        Mockito
                .when(userDao.findByEmail("emailX"))
                .thenReturn(Optional.empty());

        Mockito
                .when(credentialEncoder.encode("passwordX"))
                .thenReturn("passwordY");

        UserModificationData userModificationData = new UserModificationData();
        userModificationData.setEmail("emailX");
        userModificationData.setPassword("passwordX");
        userModificationData.setDisplayName("displayNameX");

        customerManager.add(userModificationData);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

        Mockito
                .verify(userDao, Mockito.times(1))
                .insert(argumentCaptor.capture());

        assert argumentCaptor.getValue().getId() == null;
        assert argumentCaptor.getValue().getEmail().equals("emailX");
        assert argumentCaptor.getValue().getDisplayName().equals("displayNameX");
        assert argumentCaptor.getValue().getCredential().equals("passwordY");
    }

    private User createSimpleCustomer() {
        return MutableUser.Builder
                .instance()
                .email("emailX")
                .displayName("displayNameX")
                .credential("credentialX")
                .build();
    }
}