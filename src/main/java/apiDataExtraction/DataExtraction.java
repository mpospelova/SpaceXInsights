package apiDataExtraction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.LaunchData;
import data.PayloadData;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Constants.*;

public class DataExtraction {
    private final Gson gson;

    public DataExtraction() {
        this.gson = new Gson();
    }

    public Object[] prepareDataForGraph(InputStreamReader payloadStream, InputStreamReader launchStream) {
        System.out.printf("%sStarting to extract data.", INFO);

        Type launchDataType = new TypeToken<List<LaunchData>>() {}.getType();
        Type payloadDataType = new TypeToken<List<PayloadData>>() {}.getType();
        List<PayloadData> payloadDataList = gson.fromJson(payloadStream, payloadDataType);
        List<LaunchData> launchDataList = gson.fromJson(launchStream, launchDataType);

        Map<String, PayloadData> payloadIDtoData = new HashMap<>();
        for (PayloadData payloadData : payloadDataList) {
            payloadIDtoData.put(payloadData.getId(), payloadData);
        }

        String[] lables = new String[launchDataList.size()];
        double[] values = new double[launchDataList.size()];

        for (int i = 0; i < launchDataList.size(); i++) {
            LaunchData launchData = launchDataList.get(i);
            List<String> payloadIds = launchData.getPayloads();

            double payloadWeight = 0;
            for (String payloadId : payloadIds) {
                payloadWeight += payloadIDtoData.get(payloadId).getMass_kg();
            }

            lables[i] = parseUTC(launchData.getDate_utc());
            values[i] = payloadWeight;
        }

        return new Object[]{lables, values};
    }

    private String parseUTC(String utcFormat) {
        Instant instant = Instant.parse(utcFormat);
        int year = instant.atZone(ZoneId.systemDefault()).getYear();
        int month = instant.atZone(ZoneId.systemDefault()).getMonth().getValue();

        return String.format("%d/%d", month, year);
    }
}
