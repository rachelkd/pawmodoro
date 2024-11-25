package data_access;

import entity.exceptions.CatFactRetrievalException;
import use_case.get_cat_fact.CatFactDataAccessInterface;

/**
 * In-memory implementation of CatFactDataAccessInterface for testing and development.
 */
public class InMemoryCatFactDataAccessObject implements CatFactDataAccessInterface {
    private final String predefinedCatFact;
    private final boolean shouldThrowError;
    private final String errorMessage;

    public InMemoryCatFactDataAccessObject(String predefinedCatFact, boolean shouldThrowError, String errorMessage) {
        this.predefinedCatFact = predefinedCatFact;
        this.shouldThrowError = shouldThrowError;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getCatFact() throws CatFactRetrievalException {
        if (shouldThrowError) {
            throw new CatFactRetrievalException(errorMessage);
        }
        return predefinedCatFact;
    }
}
