package data_access;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CatImage;
import use_case.cat_image.CatImageDataAccessInterface;

/**
 * Implementation of CatImageDataAccessInterface that fetches images from The
 * Cat API.
 */
public class ApiCatImageDataAccessObject implements CatImageDataAccessInterface {
    // Query API for only jpg and png images
    private static final String API_ENDPOINT = "https://api.thecatapi.com/v1/images/search?mime_types=jpg,png";
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public ApiCatImageDataAccessObject() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public CatImage fetchRandomCatImage() throws Exception {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ENDPOINT))
                .GET()
                .build();

        final HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // The API returns an array with one object
        final JsonNode jsonNode = objectMapper.readTree(response.body()).get(0);
        return new CatImage(
                jsonNode.get("id").asText(),
                jsonNode.get("url").asText());
    }
}
