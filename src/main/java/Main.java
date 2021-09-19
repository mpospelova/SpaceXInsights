import apiDataExtraction.APIConnector;
import apiDataExtraction.DataExtractor;
import graph.ImageDrawer;
import utils.Base64Converter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static utils.Constants.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String launchURL = "https://api.spacexdata.com/v5/launches";
        String payloadURL = "https://api.spacexdata.com/v4/payloads";

        start(launchURL, payloadURL);
    }

    /**
     *  This method connects to API, extracts data and draws an image.
     * @param launch Link of API for launch data.
     * @param payload Link of API for payload data.
     * @throws IOException If wrong link was entered, or if program couldn't connect to API.
     */
    public static void start(String launch, String payload) throws IOException {
        try {
            URL launchURL = new URL(launch);
            URL payloadURL = new URL(payload);
            APIConnector apiConnection = new APIConnector(launchURL, payloadURL);

            DataExtractor dataExtractor = new DataExtractor();
            Object[] graphData = dataExtractor.prepareDataForGraph(apiConnection.getPayloadStream(), apiConnection.getLaunchStream());
            String[] labels = (String[]) graphData[0];
            double[] values = (double[]) graphData[1];

            ImageDrawer imageDrawer = new ImageDrawer(values, labels);
            BufferedImage image = imageDrawer.drawImage();
            String base64String = Base64Converter.convertToBase64(image);
            System.out.printf("%sCreated the base64 string \"%s\"%n", INFO, base64String);

        } catch (MalformedURLException e) {
            String errorMessage = String.format("Wrong URL, cannot connect to API: %s", e.getMessage());
            throw new MalformedURLException(errorMessage);
        } catch (IOException e) {
            String errorMessage = e.getMessage();
            throw new IOException(errorMessage);
        }
    }
}
