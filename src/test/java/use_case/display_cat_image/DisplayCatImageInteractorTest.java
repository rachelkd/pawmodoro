package use_case.display_cat_image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data_access.InMemoryDisplayCatImageDataAccessObject;
import entity.CatImage;
import entity.CatImageFactory;

class DisplayCatImageInteractorTest {

    private final CatImageFactory catImageFactory = new CatImageFactory();

    @Test
    void successTest() {
        final InMemoryDisplayCatImageDataAccessObject catImageRepository = new InMemoryDisplayCatImageDataAccessObject();
        final CatImage expectedCatImage = catImageFactory.create("test-id", "http://example.com/cat.jpg");
        catImageRepository.setMockCatImage(expectedCatImage);

        // This creates a successPresenter that tests whether the test case is as we
        // expect
        final DisplayCatImageOutputBoundary successPresenter = new DisplayCatImageOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayCatImageOutputData catImage) {
                // Check that the output data contains the correct cat image details
                assertEquals(expectedCatImage.getImageId(), catImage.getImageId());
                assertEquals(expectedCatImage.getImageUrl(), catImage.getImageUrl());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        final DisplayCatImageInteractor interactor = new DisplayCatImageInteractor(catImageRepository,
                successPresenter);
        interactor.execute(new DisplayCatImageInputData());
    }

    @Test
    void failureApiErrorTest() {
        final InMemoryDisplayCatImageDataAccessObject catImageRepository = new InMemoryDisplayCatImageDataAccessObject();
        catImageRepository.setShouldThrowException(true);

        // This creates a failurePresenter that tests whether the test case fails as
        // expected
        final DisplayCatImageOutputBoundary failurePresenter = new DisplayCatImageOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayCatImageOutputData catImage) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to fetch cat image: Test exception", error);
            }
        };

        final DisplayCatImageInteractor interactor = new DisplayCatImageInteractor(catImageRepository,
                failurePresenter);
        interactor.execute(new DisplayCatImageInputData());
    }
}
