package use_case.signup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import entity.exceptions.DatabaseAccessException;

class SignupInteractorTest {

    @Test
    void successTest() {
        final SignupInputData inputData = new SignupInputData("Paul", "paul@example.com", "password", "password");
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("Paul", user.getUsername());
                assertFalse(user.isUseCaseFailed());
                try {
                    assertTrue(userRepository.existsByName("Paul"));
                }
                catch (DatabaseAccessException exception) {
                    fail("Database access exception should not be thrown.");
                }
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                assertTrue(true, "Successfully switched to login view");
            }
        };

        final SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter,
                new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        final SignupInputData inputData = new SignupInputData("Paul", "paul@example.com", "password", "wrong");
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
                final SignupOutputData outputData = new SignupOutputData("Paul", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("Paul", outputData.getUsername());
            }

            @Override
            public void switchToLoginView() {
                assertTrue(true, "Successfully switched to login view");
            }
        };

        final SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter,
                new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        final SignupInputData inputData = new SignupInputData("Paul", "paul@example.com", "password", "password");
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create("Paul", "paul@example.com");
        try {
            userRepository.save(user, "pwd");
        }
        catch (DatabaseAccessException exception) {
            fail("Database access exception should not be thrown.");
        }

        final SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
                final SignupOutputData outputData = new SignupOutputData("Paul", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("Paul", outputData.getUsername());
            }

            @Override
            public void switchToLoginView() {
                assertTrue(true, "Successfully switched to login view");
            }
        };

        final SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter,
                new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyFieldsTest() {
        final SignupInputData inputData = new SignupInputData("", "", "", "");
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Please fill out all fields.", error);
                final SignupOutputData outputData = new SignupOutputData("Paul", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("Paul", outputData.getUsername());
            }

            @Override
            public void switchToLoginView() {
                assertTrue(true, "Successfully switched to login view");
            }
        };

        final SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter,
                new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidEmailTest() {
        final SignupInputData inputData = new SignupInputData("Paul", "invalid-email", "password", "password");
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Please enter a valid email address.", error);
                final SignupOutputData outputData = new SignupOutputData("Paul", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("Paul", outputData.getUsername());
            }

            @Override
            public void switchToLoginView() {
                assertTrue(true, "Successfully switched to login view");
            }
        };

        final SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter,
                new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureWeakPasswordTest() {
        final SignupInputData inputData = new SignupInputData("Paul", "paul@example.com", "weak", "weak");
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password should be at least 6 characters.", error);
                final SignupOutputData outputData = new SignupOutputData("Paul", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("Paul", outputData.getUsername());
            }

            @Override
            public void switchToLoginView() {
                assertTrue(true, "Successfully switched to login view");
            }
        };

        final SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter,
                new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void switchToLoginViewTest() {
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        final SignupOutputBoundary presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                assertTrue(true, "Successfully switched to login view");
            }
        };

        final SignupInputBoundary interactor = new SignupInteractor(userRepository, presenter,
                new CommonUserFactory());
        interactor.switchToLoginView();
    }
}
