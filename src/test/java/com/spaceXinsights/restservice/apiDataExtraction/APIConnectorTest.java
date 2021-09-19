package com.spaceXinsights.restservice.apiDataExtraction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class APIConnectorTest {

    @Test
    public void exception_thrown_if_wrong_link() throws MalformedURLException {
        URL wrongURL = new URL("https://api.spacexdata.com/v5/WRONG_URL");
        assertThrows(IOException.class, () ->
                new APIConnector(wrongURL, wrongURL));
    }

    @Test
    public void correct_InputStreamReader_returned() throws IOException {
        URL correctURL = new URL("https://api.spacexdata.com/v5/launches");
        APIConnector apiConnector = new APIConnector(correctURL, correctURL);
        assertNotNull(apiConnector.getLaunchStream());
        assertNotNull(apiConnector.getPayloadStream());
    }
}
