package interface_adapter.cat_image;

import use_case.display_cat_image.CatImageOutputBoundary;
import use_case.display_cat_image.CatImageOutputData;

/**
 * Presenter for the cat image feature.
 */
public class CatImagePresenter implements CatImageOutputBoundary {
    private final CatImageViewModel viewModel;

    public CatImagePresenter(CatImageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(CatImageOutputData outputData) {
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
