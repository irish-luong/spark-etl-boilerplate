package com.max.etl.boilerplate.parameter;

import java.util.Arrays;
import java.util.Optional;

public enum CommonJobParameter implements Parameter<CommonJobParameter> {

    JOB_NAME("jobName"),
    CLIENT_ID("client_id");

    private String paramName;

    CommonJobParameter(String paramName) {
        this.paramName = paramName;
    }

    public Optional<CommonJobParameter> paramExists(String argName) {
        return Arrays.stream(values())
                .filter(commonJobParameter -> commonJobParameter.paramName.equals(argName))
                .findFirst();
    }

    @Override
    public String getParamName() {
        return paramName;
    }
}
