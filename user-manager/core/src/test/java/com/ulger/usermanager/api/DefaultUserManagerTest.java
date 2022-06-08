package com.ulger.usermanager.api;

import com.ulger.usermanager.api.data.UserDao;
import com.ulger.usermanager.api.validation.UserValidator;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserManagerTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserPreInitiator userPreInitiator;

    private UserManager userManager;

    @Before
    public void setUp() {
        userManager = new DefaultUserManager(userDao, userValidator, userPreInitiator);
    }

    @Test
    public void test_getCustomerByEmail_throws_exception_when_email_is_blank() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userManager.getUserByEmail(""));

        assert exception.getMessage().equals("Email is required");
    }

    @Test
    public void test_getUserByEmail_returns_data() {

        Mockito
                .when(userDao.findByEmail(eq("emailX")))
                .thenReturn(Optional.of(createSimpleCustomer()));

        Optional<User> user = userManager.getUserByEmail("emailX");

        assert user.isPresent();
        assert user.get().getId() == null;
        assert user.get().getEmail().equals("emailX");
        assert user.get().getDisplayName().equals("displayNameX");
        assert user.get().getCredential().equals("credentialX");
    }

    @Test
    public void test_add_trows_exception_when_invalid_data_given() {

        ValidationResult validationResult = new ValidationResult();
        validationResult.addError("errorX");

        Mockito
                .when(userValidator.validateToCreate(anyObject()))
                .thenReturn(validationResult);

        ValidationException validationException = assertThrows(
                ValidationException.class,
                () -> userManager.add(new UserModificationData()));

        assert validationException.getErrors().size() == 1;
        assert validationException.getErrors().contains("errorX");
    }

    @Test
    public void test_add_user_throws_exception_when_user_already_exists() {

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
                () -> userManager.add(userModificationData));
    }

    @Test
    public void test_add_user_successfully() {

        ValidationResult validationResult = new ValidationResult();

        Mockito
                .when(userValidator.validateToCreate(anyObject()))
                .thenReturn(validationResult);

        Mockito
                .when(userDao.findByEmail("emailX"))
                .thenReturn(Optional.empty());

        Mockito
                .when(userPreInitiator.initiate(any()))
                .thenReturn(createSimpleCustomer());

        UserModificationData userModificationData = new UserModificationData();
        userModificationData.setEmail("emailX");
        userModificationData.setRawPassword("passwordX");
        userModificationData.setDisplayName("displayNameX");

        userManager.add(userModificationData);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

        Mockito
                .verify(userDao, Mockito.times(1))
                .insert(argumentCaptor.capture());

        assert argumentCaptor.getValue().getId() == null;
        assert argumentCaptor.getValue().getEmail().equals("emailX");
        assert argumentCaptor.getValue().getDisplayName().equals("displayNameX");
        assert argumentCaptor.getValue().getCredential().equals("credentialX");
    }

    private User createSimpleCustomer() {
        return MutableUser.builder()
                .email("emailX")
                .displayName("displayNameX")
                .credential("credentialX")
                .build();
    }
}