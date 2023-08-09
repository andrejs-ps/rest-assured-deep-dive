package com.psrestassured;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateLimit {

    private int coreLimit;
    private int searchLimit;

    @JsonProperty("resources")
    public void unmarshallResources(Map<String, Object> resources) {
        var core = (Map<String, Integer>) resources.get("core");
        coreLimit = core.get("limit");

        var search = (Map<String, Integer>) resources.get("search");
        searchLimit = search.get("limit");
    }


    public int getCoreLimit() {
        return coreLimit;
    }

    public int getSearchLimit() {
        return searchLimit;
    }
}
