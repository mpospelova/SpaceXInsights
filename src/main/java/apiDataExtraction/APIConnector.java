package apiDataExtraction;

import lombok.Value;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import static utils.Constants.*;

@Value
public class APIConnector {
    InputStreamReader launchStream;
    InputStreamReader payloadStream;

    public APIConnector(URL launchURL, URL payloadURL) throws IOException {
        this.launchStream = connectToAPI(launchURL);
        this.payloadStream = connectToAPI(payloadURL);
    }

    /**
     * This method connects to the API using passed URL.. Then it extracts the response as an input stream.
     */
    public InputStreamReader connectToAPI(URL apiURL) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int response = conn.getResponseCode();
        if (response != 200) {
            String errorMessage = String.format("Could not establish connection to API %s.", apiURL.getPath());
            throw new ConnectException(String.format("%sResponseCode %d%n%s%n", ERROR, response, errorMessage));
        }

        System.out.printf("%sResponseCode %d%n", INFO, response);
        System.out.printf("%sConnection to %s established.%n", INFO, apiURL.toString());

        return new InputStreamReader(conn.getInputStream());
    }
}
