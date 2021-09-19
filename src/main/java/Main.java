import apiDataExtraction.APIConnector;
import apiDataExtraction.DataExtraction;
import graph.ImageDrawer;
import utils.Base64Converter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static utils.Constants.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String launchURL = "https://api.spacexdata.com/v5/launches";
        String payloadURL = "https://api.spacexdata.com/v4/payloads";

        start(launchURL, payloadURL);
    }

    public static void start(String lauch, String payload) throws IOException {
        try {
            URL launchURL = new URL(lauch);
            URL payloadURL = new URL(payload);
            APIConnector apiConnection = new APIConnector(launchURL, payloadURL);

            DataExtraction dataExtraction = new DataExtraction();
            Object[] graphData = dataExtraction.prepareDataForGraph(apiConnection.getPayloadStream(), apiConnection.getLaunchStream());
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
