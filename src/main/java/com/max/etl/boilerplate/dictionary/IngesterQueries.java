package com.max.etl.boilerplate.dictionary;

public class IngesterQueries {

    public static final String TABLE_NAME = "sales";

    public static final String INSERT_QUERY_SALES = "INSERT INTO " + TABLE_NAME +
            " (sale_id, date, product, quantity) VALUES (?, ?, ?, ?)";
}
