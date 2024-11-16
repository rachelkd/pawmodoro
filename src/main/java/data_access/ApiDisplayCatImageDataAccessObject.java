package data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CatImage;
import entity.CatImageFactory;
import entity.exceptions.CatImageFetchException;
import use_case.display_cat_image.DisplayCatImageDataAccessInterface;

/**
 * Implementation of DisplayCatImageDataAccessInterface that fetches images from
 * The Cat API.
 */
public class ApiDisplayCatImageDataAccessObject implements DisplayCatImageDataAccessInterface {
    // Query API for jpg/png images with small/medium size
    private static final String API_ENDPOINT = "https://api.thecatapi.com/v1/images/search"
            + "?mime_types=jpg,png"
            + "&size=small";

    private final HttpClient client;
    private final ObjectMapper objectMapper;
    private final CatImageFactory catImageFactory;

    public ApiDisplayCatImageDataAccessObject(CatImageFactory catImageFactory) {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.catImageFactory = catImageFactory;
    }

    @Override
    public CatImage fetchRandomCatImage() throws CatImageFetchException {
        try {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_ENDPOINT))
                    .GET()
                    .build();

            final HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            // The API returns an array with one object
            final JsonNode jsonNode = objectMapper.readTree(response.body()).get(0);
            return catImageFactory.create(
                    jsonNode.get("id").asText(),
                    jsonNode.get("url").asText());
        }
        catch (IOException | InterruptedException exception) {
            throw new CatImageFetchException("Failed to fetch cat image from API", exception);
        }
    }
}
