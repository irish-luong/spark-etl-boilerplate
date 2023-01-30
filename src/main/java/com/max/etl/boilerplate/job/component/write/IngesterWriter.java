package com.max.etl.boilerplate.job.component.write;

import com.max.etl.boilerplate.job.component.transformation.mapper.IngesterSaleToDbMapper;
import com.max.etl.boilerplate.persistence.manager.DatabaseConnManager;
import com.max.etl.boilerplate.schema.SaleSchema;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.springframework.beans.factory.annotation.Autowired;

public class IngesterWriter implements Writer<Dataset<Row>> {

    @Autowired
    private DatabaseConnManager databaseConnManager;

    /**
     */
    @Override
    public void write(Dataset<Row> input) {

        Dataset<Row> persistDf = input.mapPartitions(
                new IngesterSaleToDbMapper(databaseConnManager),
                RowEncoder.apply(SaleSchema.getSchema())
        );

        // Call a Spark action to trigger all transformation
        persistDf.count();

    }
}
