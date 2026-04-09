package com.ion.healthprovider.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 *  For the IDE to "resolve" the property, it needs to see a class mapped to that prefix.
 *  Instead of just using @Value, create a configuration class:
 */
@Component
@ConfigurationProperties(prefix = "app.logs")
public class LogProperties {
    private String fileLocation; // Matches 'app.logs.fileLocation'

    // We MUST have a getter and setter for the IDE to pick it up
    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
