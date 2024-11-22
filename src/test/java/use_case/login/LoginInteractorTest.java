package use_case.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;

class LoginInteractorTest {

    @Test
    void successTest() {
        final LoginInputData inputData = new LoginInputData("Paul", "password");
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // For the success test, we need to add Paul to the data access repository
        // before we log in.
        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create("Paul", "password");
        userRepository.save(user);

        // This creates a successPresenter that tests whether the test case is as we
        // expect.
        final LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            /**
             * Switches to the login View.
             */
            @Override
            public void switchToSignUpView() {
                fail("Use case switch to sign up view is unexpected.");
            }

            /**
             * Switches to the study session View.
             */
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

        // For the success test, we need to add Paul to the data access repository
        // before we log in.
        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create("Paul", "password");
        userRepository.save(user);

        // This creates a successPresenter that tests whether the test case is as we
        // expect.
        final LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", userRepository.getCurrentUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            /**
             * Switches to the login View.
             */
            @Override
            public void switchToSignUpView() {
                fail("Use case switch to sign up view is unexpected.");
            }

            /**
             * Switches to the study session View.
             */
            @Override
            public void switchToStudySessionView() {
                fail("Use case switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        assertNull(userRepository.getCurrentUsername());

        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        final LoginInputData inputData = new LoginInputData("Paul", "wrong");
        final LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // For this failure test, we need to add Paul to the data access repository
        // before we log in, and
        // the passwords should not match.
        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create("Paul", "password");
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        final LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            /**
             * Switches to the login View.
             */
            @Override
            public void switchToSignUpView() {
                fail("Use case switch to sign up view is unexpected.");
            }

            /**
             * Switches to the study session View.
             */
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

        // Add Paul to the repo so that when we check later they already exist

        // This creates a presenter that tests whether the test case is as we expect.
        final LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // this should never be reached since the test case should fail
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Paul: Account does not exist.", error);
            }

            /**
             * Switches to the login View.
             */
            @Override
            public void switchToSignUpView() {
                fail("Use case switch to sign up view is unexpected.");
            }

            /**
             * Switches to the study session View.
             */
            @Override
            public void switchToStudySessionView() {
                fail("Use case switch to study session view is unexpected.");
            }
        };

        final LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
