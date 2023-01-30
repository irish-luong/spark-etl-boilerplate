package com.max.etl.boilerplate.job.component.transformation.flatmapper;

import com.max.etl.boilerplate.schema.SaleSchema;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import scala.collection.mutable.WrappedArray;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IngesterJsonFlatMapper implements FlatMapFunction<Row, Row> {


    private static final String SELLER_COLUMN = "Seller_Id";
    private static final String SALE_COLUMN = "Sale";

    private static final String ITEM_COLUMN = "Items";

    /**
     */
    @Override
    public Iterator<Row> call(Row row) throws Exception {
        return explodeSalesPerSellerIntoRows(row).iterator();
    }

    private List<Row> explodeSalesPerSellerIntoRows(Row inputRow) {
        List<Row> rows = new ArrayList<>();

        String sellerId = inputRow.getAs(SELLER_COLUMN);

        WrappedArray<Row> saleValues = inputRow.getAs(SALE_COLUMN);

        Row[] sales = saleValues.array();

        for(Row row : sales) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            Date saleDate = Date.valueOf(LocalDate.parse(row.getAs("Date"), formatter));

            WrappedArray<Row> itemsRows = row.getAs(ITEM_COLUMN);

            Row[] items = itemsRows.array();

            for(Row rowItem : items) {

                int indexValues = 0;
                Object[] rowValues = new Object[SaleSchema.values().length];

                rowValues[indexValues++] = sellerId;
                rowValues[indexValues++] = saleDate;
                rowValues[indexValues++] = rowItem.getAs("Product");
                rowValues[indexValues++] = rowItem.getAs("Quantity");

                rows.add( new GenericRowWithSchema(rowValues, SaleSchema.getSchema()));
            }

        }

        return rows;
    }
}
