package use_case.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        final LoginInputData inputData = new LoginInputData("Paul", "password");
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create("Paul", "password");
        try {
            userRepository.save(user);
        }
        catch (DatabaseAccessException exception) {
            fail("Database access exception should not be thrown.");
        }

        final LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignUpView() {
                fail("Use case switch to sign up view is unexpected.");
            }

            @Override
            public void switchToStudySessionView() {
                fail("Use case switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successUserLoggedInTest() {
        final LoginInputData inputData = new LoginInputData("Paul", "password");
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create("Paul", "password");
        try {
            userRepository.save(user);
        }
        catch (DatabaseAccessException exception) {
            fail("Database access exception should not be thrown.");
        }

        final LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                try {
                    assertEquals("Paul", userRepository.getCurrentUsername());
                }
                catch (DatabaseAccessException exception) {
                    fail("Database access exception should not be thrown.");
                }
                assertEquals("Paul", user.getUsername());
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignUpView() {
                fail("Use case switch to sign up view is unexpected.");
            }

            @Override
            public void switchToStudySessionView() {
                fail("Use case switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        try {
            assertNull(userRepository.getCurrentUsername());
        }
        catch (DatabaseAccessException exception) {
            fail("Database access exception should not be thrown.");
        }

        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        final LoginInputData inputData = new LoginInputData("Paul", "wrong");
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create("Paul", "password");
        try {
            userRepository.save(user);
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
                assertEquals("Incorrect password for \"Paul\".", error);
                final LoginOutputData outputData = new LoginOutputData("Paul", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("Paul", outputData.getUsername());
            }

            @Override
            public void switchToSignUpView() {
                fail("Use case switch to sign up view is unexpected.");
            }

            @Override
            public void switchToStudySessionView() {
                fail("Use case switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserDoesNotExistTest() {
        final LoginInputData inputData = new LoginInputData("Paul", "password");
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Paul: Account does not exist.", error);
                final LoginOutputData outputData = new LoginOutputData("Paul", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("Paul", outputData.getUsername());
            }

            @Override
            public void switchToSignUpView() {
                fail("Use case switch to sign up view is unexpected.");
            }

            @Override
            public void switchToStudySessionView() {
                fail("Use case switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToSignUpViewTest() {
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        final LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignUpView() {
                // Test passes if this method is called
                assertTrue(true);
            }

            @Override
            public void switchToStudySessionView() {
                fail("Use case switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, presenter);
        interactor.switchToSignUpView();
    }
}
