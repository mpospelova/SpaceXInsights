import apiDataExtraction.APIConnector;
import apiDataExtraction.DataExtraction;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static utils.Constants.*;

public class Main {

    public static void main(String[] args) {
        start();
    }
    
    public static void start() {
        final URL launchURL;
        final URL payloadURL;
        try {
            launchURL = new URL("https://api.spacexdata.com/v5/launches");
            payloadURL = new URL("https://api.spacexdata.com/v4/payloads");
            APIConnector apiConnection = new APIConnector(launchURL, payloadURL);

            System.out.printf("%sStarting to extract data.", INFO);

            DataExtraction dataExtraction = new DataExtraction();
            Object[] graphData = dataExtraction.prepareDataForGraph(apiConnection.getPayloadStream(), apiConnection.getLaunchStream());
            String[] lables = (String[]) graphData[0];
            double[] values = (double[]) graphData[1];

        } catch (IOException e) {
            System.out.printf("%sCould not connect to API.", INFO);
            return;
        }
    }
}
