package use_case.change_password;

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

class ChangePasswordInteractorTest {
    @Test
    void successTest() {
        // Given a user with an old password
        final String username = "Paul";
        final String email = "paul@example.com";
        final String oldPassword = "oldpassword";
        final String newPassword = "newpassword123";

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create(username, email);

        final InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user, oldPassword);

        // Verify user exists before attempting change
        assertTrue(userRepository.existsByName(username));
        try {
            userRepository.authenticate(email, oldPassword);
        }
        catch (DatabaseAccessException exception) {
            fail("Authentication should succeed with old password");
        }

        // When changing password
        final ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                // Then verify success view is prepared with correct data
                assertEquals(username, user.getUsername());
                assertFalse(user.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }
        };

        final ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(
                userRepository, successPresenter, factory);

        final ChangePasswordInputData inputData = new ChangePasswordInputData(newPassword, username);
        interactor.execute(inputData);

        // And verify password was actually changed
        try {
            userRepository.authenticate(email, newPassword);
        }
        catch (DatabaseAccessException exception) {
            fail("Authentication should succeed with new password");
        }
    }

    @Test
    void failureUserDoesNotExistTest() {
        // Given a non-existent user
        final String username = "NonexistentUser";
        final String newPassword = "newpassword";

        final UserFactory factory = new CommonUserFactory();
        final InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Verify user doesn't exist
        assertFalse(userRepository.existsByName(username));

        // When attempting to change password
        final ChangePasswordOutputBoundary failurePresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // Then verify failure view is prepared with correct error
                assertEquals("User does not exist.", error);
            }
        };

        final ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(
                userRepository, failurePresenter, factory);

        final ChangePasswordInputData inputData = new ChangePasswordInputData(username, newPassword);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyPasswordTest() {
        // Given a user with an empty new password
        final String username = "Paul";
        final String email = "paul@example.com";
        final String oldPassword = "oldpassword";
        final String newPassword = "";

        final UserFactory factory = new CommonUserFactory();
        final User user = factory.create(username, email);

        final InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user, oldPassword);

        // Verify user exists and can authenticate
        assertTrue(userRepository.existsByName(username));
        try {
            userRepository.authenticate(email, oldPassword);
        }
        catch (DatabaseAccessException exception) {
            fail("Authentication should succeed with old password");
        }

        // When attempting to change to empty password
        final ChangePasswordOutputBoundary failurePresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // Then verify failure view is prepared with correct error
                assertEquals("Password cannot be empty.", error);
            }
        };

        final ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(
                userRepository, failurePresenter, factory);

        final ChangePasswordInputData inputData = new ChangePasswordInputData(newPassword, username);
        interactor.execute(inputData);

        // And verify password was not changed
        try {
            userRepository.authenticate(email, oldPassword);
        }
        catch (DatabaseAccessException exception) {
            fail("Authentication should still succeed with old password");
        }
    }
}
