package data;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class LaunchData {
    List<String> payloads;
    String id;
    boolean success;
    String date_utc;
}
