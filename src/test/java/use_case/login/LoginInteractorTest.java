package use_case.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.exceptions.DatabaseAccessException;

class LoginInteractorTest {

    @Test
    void successTest() {
        final String username = "Paul";
        final String email = "paul@example.com";
        final String password = "password";
        final LoginInputData inputData = new LoginInputData(username, password);
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create(username, email);
        try {
            userRepository.save(user, password);
        }
        catch (DatabaseAccessException exception) {
            fail("Database access exception should not be thrown.");
        }

        final LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals(username, user.getUsername());
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }

            @Override
            public void switchToSignUpView() {
                fail("Switch to sign up view is unexpected.");
            }

            @Override
            public void switchToStudySessionView() {
                fail("Switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordIncorrectTest() {
        final String username = "Paul";
        final String email = "paul@example.com";
        final String correctPassword = "password";
        final String wrongPassword = "wrong";
        final LoginInputData inputData = new LoginInputData(username, wrongPassword);
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create(username, email);
        try {
            userRepository.save(user, correctPassword);
        }
        catch (DatabaseAccessException exception) {
            fail("Database access exception should not be thrown.");
        }

        final LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password.", error);
            }

            @Override
            public void switchToSignUpView() {
                fail("Switch to sign up view is unexpected.");
            }

            @Override
            public void switchToStudySessionView() {
                fail("Switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserNotFoundTest() {
        final String username = "Paul";
        final String otherUsername = "NotPaul";
        final String email = "notpaul@example.com";
        final String password = "password";
        final LoginInputData inputData = new LoginInputData(username, password);
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create(otherUsername, email);
        try {
            userRepository.save(user, password);
        }
        catch (DatabaseAccessException exception) {
            fail("Database access exception should not be thrown.");
        }

        final LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User does not exist.", error);
            }

            @Override
            public void switchToSignUpView() {
                fail("Switch to sign up view is unexpected.");
            }

            @Override
            public void switchToStudySessionView() {
                fail("Switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
