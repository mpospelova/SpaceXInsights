package com.spaceXinsights.restservice.apiDataExtraction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spaceXinsights.restservice.data.LaunchData;
import com.spaceXinsights.restservice.data.PayloadData;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spaceXinsights.restservice.utils.Constants.*;

public class DataExtractor {
    private final Gson gson;

    public DataExtractor() {
        this.gson = new Gson();
    }

    /**
     * This method uses com.spaceXinsights.restservice.data streams to extract com.spaceXinsights.restservice.data for launches and payloads. After extraction, it prepares the com.spaceXinsights.restservice.data
     * for com.spaceXinsights.restservice.graph drawing.
     */
    public Object[] prepareDataForGraph(InputStreamReader payloadStream, InputStreamReader launchStream) {
        System.out.printf("%sStarting to extract com.spaceXinsights.restservice.data.", INFO);

        if(payloadStream == null || launchStream == null) {
            String errorMessage = String.format("%s An empty stream was passed.", ERROR);
            throw new IllegalArgumentException(errorMessage);
        }

        Type launchDataType = new TypeToken<List<LaunchData>>() {}.getType();
        Type payloadDataType = new TypeToken<List<PayloadData>>() {}.getType();
        List<PayloadData> payloadDataList = gson.fromJson(payloadStream, payloadDataType);
        List<LaunchData> launchDataList = gson.fromJson(launchStream, launchDataType);

        Map<String, PayloadData> payloadIDtoData = new HashMap<>();
        for (PayloadData payloadData : payloadDataList) {
            payloadIDtoData.put(payloadData.getId(), payloadData);
        }

        String[] labels = new String[launchDataList.size()];
        double[] values = new double[launchDataList.size()];

        for (int i = 0; i < launchDataList.size(); i++) {
            LaunchData launchData = launchDataList.get(i);
            List<String> payloadIds = launchData.getPayloads();

            double payloadWeight = 0;
            for (String payloadId : payloadIds) {
                payloadWeight += payloadIDtoData.get(payloadId).getMass_kg();
            }

            labels[i] = parseUTC(launchData.getDate_utc());
            values[i] = payloadWeight;
        }

        return new Object[]{labels, values};
    }

    private String parseUTC(String utcFormat) {
        Instant instant = Instant.parse(utcFormat);
        int year = instant.atZone(ZoneId.systemDefault()).getYear();
        int month = instant.atZone(ZoneId.systemDefault()).getMonth().getValue();

        return String.format("%d/%d", month, year);
    }
}
