package interface_adapter.display_cat_image;

import use_case.display_cat_image.DisplayCatImageOutputBoundary;
import use_case.display_cat_image.DisplayCatImageOutputData;

/**
 * Presenter for the cat image feature.
 */
public class DisplayCatImagePresenter implements DisplayCatImageOutputBoundary {
    private final DisplayCatImageViewModel viewModel;

    public DisplayCatImagePresenter(DisplayCatImageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(DisplayCatImageOutputData outputData) {
        viewModel.setImageUrl(outputData.getImageUrl());
        viewModel.setErrorMessage("");
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        viewModel.setErrorMessage(error);
        viewModel.firePropertyChanged();
    }
} 
