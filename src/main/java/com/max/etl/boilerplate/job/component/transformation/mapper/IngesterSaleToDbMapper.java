package com.max.etl.boilerplate.job.component.transformation.mapper;

import com.max.etl.boilerplate.dictionary.IngesterQueries;
import com.max.etl.boilerplate.persistence.manager.DatabaseConnManager;
import com.max.etl.boilerplate.schema.SaleSchema;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

public class IngesterSaleToDbMapper implements MapPartitionsFunction<Row, Row> {


    private final DatabaseConnManager databaseConnManager;

    public IngesterSaleToDbMapper(DatabaseConnManager databaseConnManager) {
        this.databaseConnManager = databaseConnManager;
    }

    /**
     * @param iterator
     * @return
     * @throws Exception
     */
    @Override
    public Iterator<Row> call(Iterator<Row> iterator) throws Exception {
        return doBatchInsert(iterator);
    }

    private Iterator<Row> doBatchInsert(Iterator<Row> iterator) throws SQLException {

        Connection connection = databaseConnManager.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(IngesterQueries.INSERT_QUERY_SALES);

        while(iterator.hasNext()) {
            int index = 1;
            Row row = iterator.next();

            preparedStatement.setString(index++, row.getAs(SaleSchema.SALE_ID.name()));
            preparedStatement.setDate(index++, row.getAs(SaleSchema.DATE.name()));
            preparedStatement.setString(index++, row.getAs(SaleSchema.PRODUCT.name()));
            preparedStatement.setLong(index++, row.getAs(SaleSchema.QUANTITY.name()));

            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        return iterator;
    }


}
