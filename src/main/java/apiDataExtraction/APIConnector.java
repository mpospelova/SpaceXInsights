package apiDataExtraction;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import static utils.Constants.*;

public class APIConnector {
    private final InputStreamReader launchURL;
    private final InputStreamReader payloadURL;

    public APIConnector(URL launchURL, URL payloadURL) throws IOException {
        this.launchURL = connectToAPI(launchURL);
        this.payloadURL = connectToAPI(payloadURL);
    }

    public InputStreamReader connectToAPI(URL apiURL) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int response = conn.getResponseCode();
        if (response != 200) {
            String errorMessage = "Could not establish connection to API.";
            throw new ConnectException(String.format("%sResponseCode %d%n%s%n", ERROR, response, errorMessage));
        }

        System.out.printf("%sResponseCode %d%n", INFO, response);
        System.out.printf("%sConnection to %s established.%n", INFO, apiURL.toString());

        return new InputStreamReader(conn.getInputStream());
    }
}
