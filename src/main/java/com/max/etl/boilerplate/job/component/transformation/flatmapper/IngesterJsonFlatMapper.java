package com.max.etl.boilerplate.job.component.transformation.flatmapper;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Row;

import java.util.Iterator;

public class IngesterJsonFlatMapper implements FlatMapFunction<Row, Row> {


    private static final String SELLER_COLUMN = "Seller_Id";
    private static final String SALE_COLUMN = "Sale_Column";

    /**
     * @param row
     * @return
     * @throws Exception
     */
    @Override
    public Iterator<Row> call(Row row) throws Exception {
        return null;
    }
}
