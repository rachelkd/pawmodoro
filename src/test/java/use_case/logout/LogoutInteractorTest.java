package use_case.logout;

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

class LogoutInteractorTest {

    @Test
    void successTest() {
        final LogoutInputData inputData = new LogoutInputData("Paul");
        final InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create("Paul", "password");
        userRepository.save(user);
        userRepository.setCurrentUsername("Paul");

        final LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData user) {
                assertEquals("Paul", user.getUsername());
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        final LogoutInputBoundary interactor = new LogoutInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
        assertNull(userRepository.getCurrentUsername());
    }

    @Test
    void failureUserNotLoggedInTest() {
        final LogoutInputData inputData = new LogoutInputData("Paul");
        final InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        final LogoutOutputBoundary failurePresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No user is currently logged in.", error);
                final LogoutOutputData outputData = new LogoutOutputData("Paul", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("Paul", outputData.getUsername());
            }
        };

        final LogoutInputBoundary interactor = new LogoutInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
