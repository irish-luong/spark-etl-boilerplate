package com.max.etl.boilerplate.job.component.process;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;


public class IngesterProcessor implements Processor<Dataset<Row>> {


    /**
     * @param input
     * @return
     */
    @Override
    public Dataset<Row> process(Dataset<Row> input) {
        return null;
    }
}
