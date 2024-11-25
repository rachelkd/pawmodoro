package use_case.get_cat_fact;

import entity.exceptions.CatFactRetrievalException;

/**
 * Interface for accessing cat facts from an external data source.
 */
public interface CatFactDataAccessInterface {
    /**
     * Retrieves a random cat fact from the data source.
     * 
     * @return the cat fact as a string
     * @throws CatFactRetrievalException if there's an error retrieving the fact
     */
    String getCatFact() throws CatFactRetrievalException;
}
