package com.nclh.retryFailedTests;

import io.cucumber.java.Scenario;

import java.util.HashMap;
import java.util.Map;

public class RetryTracker {
    private static final Map<String, Integer> retryMap = new HashMap<>();

    // Get the current retry count for a given scenario
    public static int getCurrentRetryCountForScenario(Scenario scenarioName) {
        return retryMap.getOrDefault(scenarioName, 0);
    }

    // Increment the retry count for a given scenario
    public static void incrementRetryCountForScenario(Scenario scenarioName) {
        retryMap.put(String.valueOf(scenarioName), getCurrentRetryCountForScenario(scenarioName) + 1);
    }

    // Reset the retry count for a scenario (optional, if needed for cleanup)
    public static void resetRetryCountForScenario(String scenarioName) {
        retryMap.remove(scenarioName);
    }
}
