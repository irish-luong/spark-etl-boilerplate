package com.max.etl.boilerplate.parameter;

import java.util.Arrays;
import java.util.Optional;

public enum JobParameters {

    INGESTER_JOB("ingester_job", IngesterJobParams.values());


    private final String jobName;
    private final Parameter[] parameters;

    JobParameters(String jobName, Parameter[] parameters) {
        this.jobName = jobName;
        this.parameters = parameters;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public static Optional<JobParameters> getParamForJob(String job) {
        return Arrays.stream(values())
                .filter(jobParameter -> jobParameter.jobName.equals(job))
                .findFirst();
    }

}
