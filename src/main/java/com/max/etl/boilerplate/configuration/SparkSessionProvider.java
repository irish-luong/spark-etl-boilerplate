package com.max.etl.boilerplate.configuration;


import org.apache.spark.sql.SparkSession;

import java.util.Objects;

/**
 * Class provide a SparkSession to a job
 * It follows singleton pattern to provide only one session in a job
 */
public final class SparkSessionProvider {

    private static SparkSession sparkSession;

    public static SparkSession provideSession(String masterMode) {
        if(Objects.isNull(sparkSession)) {
            SparkSession.Builder sparkBuilder = SparkSession.builder().appName("SparkBatchApp");

            if(Objects.nonNull(masterMode)) {
                sparkBuilder = sparkBuilder.master(masterMode);
            }

            sparkSession = sparkBuilder.getOrCreate();
        }
        return sparkSession;
    }
}
