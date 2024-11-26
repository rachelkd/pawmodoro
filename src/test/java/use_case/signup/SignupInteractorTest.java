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

class SignupInteractorTest {

    @Test
    void successTest() {
        final SignupInputData inputData = new SignupInputData("Paul", "password", "password");
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("Paul", user.getUsername());
                assertFalse(user.isUseCaseFailed());
                assertTrue(userRepository.existsByName("Paul"));
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
        final SignupInputData inputData = new SignupInputData("Paul", "password", "wrong");
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
        final SignupInputData inputData = new SignupInputData("Paul", "password", "password");
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create("Paul", "pwd");
        userRepository.save(user);

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
    void failureEmptyUsernameTest() {
        final SignupInputData inputData = new SignupInputData("", "password", "password");
        final SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        final SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Please fill out all fields.", error);
                final SignupOutputData outputData = new SignupOutputData("", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("", outputData.getUsername());
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
    void failureEmptyPasswordTest() {
        final SignupInputData inputData = new SignupInputData("Paul", "", "");
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
