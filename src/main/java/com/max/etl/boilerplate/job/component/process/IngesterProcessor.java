package com.max.etl.boilerplate.job.component.process;

import com.max.etl.boilerplate.job.component.transformation.flatmapper.IngesterJsonFlatMapper;
import com.max.etl.boilerplate.schema.SaleSchema;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;


public class IngesterProcessor implements Processor<Dataset<Row>> {


    /**
     * @param input
     * @return
     */
    @Override
    public Dataset<Row> process(Dataset<Row> input) {

        return input.flatMap(
                new IngesterJsonFlatMapper(),
                RowEncoder.apply(SaleSchema.getSchema())
        );
    }
}
