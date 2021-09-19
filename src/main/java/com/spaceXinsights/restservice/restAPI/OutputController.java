package com.spaceXinsights.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class OutputController {

    /**
     * When the web-address is called, this method calls the entry point and creates a graph which is then
     * displayed in the browser.
     */
    @GetMapping("/image")
    public String imageOutput() throws IOException {
        String launch = "https://api.spacexdata.com/v5/launches";
        String payload = "https://api.spacexdata.com/v4/payloads";

        EntryPoint entryPoint = new EntryPoint();
        String base64 = entryPoint.start(launch, payload);

        return String.format("<img src=\"data:image/png;base64,%s\"/>", base64);
    }

    @GetMapping("/")
    public String defaultOutput() throws IOException {
        return "Please go to http://localhost:8080/image to see results.";
    }
}
