package com.max.etl.boilerplate.dictionary;

public enum IngestJobConfig {

    INPUT_PATH("input_path");

    private final String configName;

    IngestJobConfig(String configName) {
        this.configName = configName;
    }

    public String getConfigName() {
        return configName;
    }
}
