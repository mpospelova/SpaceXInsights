package com.spaceXinsights.restservice.data;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PayloadData {
    String id;
    String launch;
    double mass_kg;
}

