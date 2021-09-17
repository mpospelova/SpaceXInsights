import apiDataExtraction.APIConnector;

import java.io.IOException;
import java.net.URL;

import static utils.Constants.*;

public class Main {

    public static void main(String[] args) {
        final URL launchURL;
        final URL payloadURL;
        try {
            launchURL = new URL("https://api.spacexdata.com/v5/launches");
            payloadURL = new URL("https://api.spacexdata.com/v5/payload");
            APIConnector apiConnection = new APIConnector(launchURL, payloadURL);

        } catch (IOException e) {
            System.out.printf("%sCould not connect to API.", INFO);
            return;
        }
    }
}
