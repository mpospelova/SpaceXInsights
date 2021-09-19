package com.spaceXinsights.restservice;

import com.spaceXinsights.restservice.EntryPoint;

import java.io.IOException;

import static com.spaceXinsights.restservice.utils.Constants.INFO;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.printf("%sStarted graph creation.%n", INFO);

        String launch = "https://api.spacexdata.com/v5/launches";
        String payload = "https://api.spacexdata.com/v4/payloads";

        EntryPoint entryPoint = new EntryPoint();
        entryPoint.start(launch, payload);
    }
}
