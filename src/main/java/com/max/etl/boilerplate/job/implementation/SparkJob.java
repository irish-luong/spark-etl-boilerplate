package com.max.etl.boilerplate.job.implementation;

import com.max.etl.boilerplate.job.Job;
import com.max.etl.boilerplate.parameter.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SparkJob extends Job<Dataset<Row>> {

    @Autowired
    private Parameters parameters;

    @Override
    protected Dataset<Row> preProcess() {
        return null;
    }

    @Override
    protected Dataset<Row> process(Dataset<Row> preProcessOutput) {
        return preProcessOutput;
    }

    @Override
    protected void postProcess(Dataset<Row> processOutput) {
        log.info("Done Spark job");
    }
}
