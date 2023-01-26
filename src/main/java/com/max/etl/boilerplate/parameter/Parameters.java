package com.max.etl.boilerplate.parameter;


import org.springframework.stereotype.Component;

import java.util.*;


/*
This class is used in {@link com.max.etl.boilerplate.job.implementation;} implementation, after said class
 * is created and registered as a Spring Bean.
 */
@Component
public class Parameters {

    private static final String ARG_SEPARATOR = "=";
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private final Map<Parameter, String> jobParams = new HashMap<>();

    /*
    Load application.properties to parse and store in jobParams hash map.
     */
    public void loadAllParams(String[] appArgs, Parameter[] parameters) {
        Map<String, String> keyValues = new HashMap<>();

        Arrays.stream(appArgs).forEach(arg -> {
            String[] keyValue = arg.split(ARG_SEPARATOR);
            keyValues.put(keyValue[KEY_INDEX], keyValue[VALUE_INDEX]);
        });

        Arrays.stream(parameters).forEach(parameter -> {
            String value = keyValues.get(parameter.getParamName());
            if(Objects.nonNull(value)) {
                jobParams.put(parameter, value);
            }
        });
    }

    public String getParameterValue(Parameter parameter) {
        return jobParams.get(parameter);
    }
}
