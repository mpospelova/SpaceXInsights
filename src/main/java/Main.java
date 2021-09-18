import apiDataExtraction.APIConnector;
import apiDataExtraction.DataExtraction;
import graph.ImageDrawer;
import utils.Base64Converter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static utils.Constants.*;

public class Main {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try {
            URL launchURL = new URL("https://api.spacexdata.com/v5/launches");
            URL payloadURL = new URL("https://api.spacexdata.com/v4/payloads");
            APIConnector apiConnection = new APIConnector(launchURL, payloadURL);

            System.out.printf("%sStarting to extract data.", INFO);

            DataExtraction dataExtraction = new DataExtraction();
            Object[] graphData = dataExtraction.prepareDataForGraph(apiConnection.getPayloadStream(), apiConnection.getLaunchStream());
            String[] lables = (String[]) graphData[0];
            double[] values = (double[]) graphData[1];

            ImageDrawer imageDrawer = new ImageDrawer(values, lables);
            BufferedImage image = imageDrawer.drawImage();
            String base64String = Base64Converter.convertToBase64(image);
            System.out.printf("%sCreated the base64 string \"%s\"%n", INFO, base64String);

        } catch (IOException e) {
            System.out.printf("%sCould not connect to API.", INFO);
            return;
        }
    }
}
