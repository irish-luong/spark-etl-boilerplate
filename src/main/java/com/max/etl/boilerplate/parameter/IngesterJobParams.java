package com.max.etl.boilerplate.parameter;

public enum IngesterJobParams implements Parameter<IngesterJobParams>{

    READ_FORMAT("readFormat"),
    FILE_FORMAT("fileFormat");

    private final String paramName;
    IngesterJobParams(String paramName) {
        this.paramName = paramName;
    }

    /**
     * @return paramName
     */
    @Override
    public String getParamName() {
        return paramName;
    }
}
