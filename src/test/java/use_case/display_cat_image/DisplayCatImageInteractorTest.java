package use_case.display_cat_image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import data_access.InMemoryDisplayCatImageDataAccessObject;
import entity.CatImage;
import entity.CatImageFactory;
import entity.exceptions.CatImageFetchException;

class DisplayCatImageInteractorTest {

    private final CatImageFactory catImageFactory = new CatImageFactory();

    @Test
    void successTest() {
        final InMemoryDisplayCatImageDataAccessObject catImageRepository =
                new InMemoryDisplayCatImageDataAccessObject();
        final CatImage expectedCatImage = catImageFactory.create("test-id", "http://example.com/cat.jpg");
        catImageRepository.setMockCatImage(expectedCatImage);

        final DisplayCatImageOutputBoundary successPresenter = new DisplayCatImageOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayCatImageOutputData catImage) {
                assertEquals(expectedCatImage.getImageId(), catImage.getImageId());
                assertEquals(expectedCatImage.getImageUrl(), catImage.getImageUrl());
                assertFalse(catImage.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        final DisplayCatImageInteractor interactor = new DisplayCatImageInteractor(catImageRepository,
                successPresenter);
        interactor.execute();
    }

    @Test
    void failureApiErrorTest() {
        final InMemoryDisplayCatImageDataAccessObject catImageRepository =
                new InMemoryDisplayCatImageDataAccessObject();
        catImageRepository.setShouldThrowException(true);

        final DisplayCatImageOutputBoundary failurePresenter = new DisplayCatImageOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayCatImageOutputData catImage) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to fetch cat image: Test exception", error);
                final DisplayCatImageOutputData outputData = new DisplayCatImageOutputData("", "", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("", outputData.getImageId());
                assertEquals("", outputData.getImageUrl());
            }
        };

        final DisplayCatImageInteractor interactor = new DisplayCatImageInteractor(catImageRepository,
                failurePresenter);
        interactor.execute();
    }

    @Test
    void failureNullImageTest() {
        final InMemoryDisplayCatImageDataAccessObject catImageRepository =
                new InMemoryDisplayCatImageDataAccessObject();
        catImageRepository.setMockCatImage(null);

        final DisplayCatImageOutputBoundary failurePresenter = new DisplayCatImageOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayCatImageOutputData catImage) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to fetch cat image: No cat image found", error);
                final DisplayCatImageOutputData outputData = new DisplayCatImageOutputData("", "", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("", outputData.getImageId());
                assertEquals("", outputData.getImageUrl());
            }
        };

        final DisplayCatImageInteractor interactor = new DisplayCatImageInteractor(catImageRepository,
                failurePresenter);
        interactor.execute();
    }

    @Test
    void failureCatImageFetchExceptionTest() {
        final DisplayCatImageDataAccessInterface catImageRepository = new DisplayCatImageDataAccessInterface() {
            @Override
            public CatImage fetchRandomCatImage() throws CatImageFetchException {
                throw new CatImageFetchException("Network error");
            }
        };

        final DisplayCatImageOutputBoundary failurePresenter = new DisplayCatImageOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayCatImageOutputData catImage) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to fetch cat image: Network error", error);
                final DisplayCatImageOutputData outputData = new DisplayCatImageOutputData("", "", true);
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("", outputData.getImageId());
                assertEquals("", outputData.getImageUrl());
            }
        };

        final DisplayCatImageInteractor interactor = new DisplayCatImageInteractor(catImageRepository,
                failurePresenter);
        interactor.execute();
    }
}
