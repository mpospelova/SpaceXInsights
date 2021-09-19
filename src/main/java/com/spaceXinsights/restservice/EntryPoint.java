package com.spaceXinsights.restservice;

import com.spaceXinsights.restservice.apiDataExtraction.APIConnector;
import com.spaceXinsights.restservice.apiDataExtraction.DataExtractor;
import com.spaceXinsights.restservice.graph.ImageDrawer;
import com.spaceXinsights.restservice.utils.Base64Converter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.spaceXinsights.restservice.utils.Constants.*;

public class EntryPoint {
    /**
     *  This method connects to API, extracts data and draws an image.
     */
    public String start(String launch, String payload) throws IOException {
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
            String base64String = Base64Converter.decode(image);
            System.out.printf("%sCreated the base64 string \"%s\"%n", INFO, base64String);

            return base64String;

        } catch (MalformedURLException e) {
            String errorMessage = String.format("Wrong URL, cannot connect to API: %s", e.getMessage());
            throw new MalformedURLException(errorMessage);
        } catch (IOException e) {
            String errorMessage = e.getMessage();
            throw new IOException(errorMessage);
        }
    }
}
