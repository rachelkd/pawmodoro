package data_access;

import java.io.IOException;

import org.json.JSONObject;

import constants.Constants;
import entity.exceptions.CatFactRetrievalException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.get_cat_fact.CatFactDataAccessInterface;

/**
 * Data access object for retrieving cat facts from the Cat Facts API.
 */
public class ApiCatFactDataAccessObject implements CatFactDataAccessInterface {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public String getCatFact() throws CatFactRetrievalException {
        try {
            final Request request = new Request.Builder()
                    .url(Constants.CAT_FACT_API_URL + "?max_length=" + Constants.CAT_FACT_MAX_LENGTH)
                    .get()
                    .build();

            final Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new CatFactRetrievalException(Constants.CAT_FACT_ERROR_API);
            }

            final String responseBody = response.body().string();
            final JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.getString("fact");
        }
        catch (IOException exception) {
            throw new CatFactRetrievalException(Constants.CAT_FACT_ERROR_NETWORK);
        }
    }
}
