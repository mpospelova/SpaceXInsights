package com.spaceXinsights.restservice.apiDataExtraction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spaceXinsights.restservice.data.LaunchData;
import com.spaceXinsights.restservice.data.PayloadData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

public class DataExtractorTest {
    List<PayloadData> payloadDataList;
    List<LaunchData> launchDataList;
    URL launchURL;
    URL payloadURL;

    @BeforeEach
    void setup() throws IOException {
        Gson gson = new Gson();

        launchURL = new URL("https://api.spacexdata.com/v5/launches");
        payloadURL = new URL("https://api.spacexdata.com/v4/payloads");

        APIConnector apiConnection = new APIConnector(launchURL, payloadURL);

        Type launchDataType = new TypeToken<List<LaunchData>>() {}.getType();
        Type payloadDataType = new TypeToken<List<PayloadData>>() {}.getType();
        payloadDataList = gson.fromJson(apiConnection.getPayloadStream(), payloadDataType);
        launchDataList = gson.fromJson(apiConnection.getLaunchStream(), launchDataType);
    }

    @Test
    public void correct_data_size_returned() throws IOException {
        APIConnector apiConnection = new APIConnector(launchURL, payloadURL);
        DataExtractor dataExtractor = new DataExtractor();
        Object[] data = dataExtractor.prepareDataForGraph(apiConnection.getPayloadStream(), apiConnection.getLaunchStream());
        String[] labels = (String[]) data[0];
        double[] values = (double[]) data[1];

        assertEquals(launchDataList.size(), values.length);
        assertEquals(launchDataList.size(), labels.length);
    }

    @Test
    public void check_exception_is_thrown_if_inputstream_empty() {
        DataExtractor dataExtractor = new DataExtractor();

        assertThrows(IllegalArgumentException.class, () ->
                dataExtractor.prepareDataForGraph(null, null));
    }
}
