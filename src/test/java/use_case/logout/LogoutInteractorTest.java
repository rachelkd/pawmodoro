package use_case.logout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;

class LogoutInteractorTest {
    @Test
    void successTest() {
        // Given a logged-in user
        final String username = "Paul";
        final String email = "paul@example.com";
        final String password = "password";

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create(username, email);

        final InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user, password);
        userRepository.setCurrentUsername(username);

        // When logging out
        final LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData outputData) {
                // Then verify the user is logged out
                assertNull(userRepository.getCurrentUsername());
                assertEquals(username, outputData.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }
        };

        final LogoutInputBoundary interactor = new LogoutInteractor(userRepository, successPresenter);
        final LogoutInputData inputData = new LogoutInputData(username);
        interactor.execute(inputData);
    }

    @Test
    void failureUserNotLoggedInTest() {
        // Given no user is logged in
        final InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // When attempting to logout
        final LogoutOutputBoundary failurePresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No user is currently logged in.", error);
            }
        };

        final LogoutInputBoundary interactor = new LogoutInteractor(userRepository, failurePresenter);
        final LogoutInputData inputData = new LogoutInputData(null);
        interactor.execute(inputData);
    }
}
