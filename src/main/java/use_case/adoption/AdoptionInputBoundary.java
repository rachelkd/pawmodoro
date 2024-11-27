package use_case.adoption;

/**
 * Input boundary for actions related to adopting a cat.
 */
public interface AdoptionInputBoundary {

    /**
     * Executes the adoption use case.
     *
     * @param adoptionInputData the input data
     */
    void execute(AdoptionInputData adoptionInputData);

}
