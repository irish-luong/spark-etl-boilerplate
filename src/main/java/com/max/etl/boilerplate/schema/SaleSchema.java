package com.max.etl.boilerplate.schema;

import org.apache.spark.sql.types.*;

public enum SaleSchema {

    SALE_ID("sale_id", DataTypes.StringType, false, null),
    DATE("date", DataTypes.DateType, false, null),
    PRODUCT("product", DataTypes.StringType, false, null),
    QUANTITY("quantity", DataTypes.LongType, false, null)
    ;

    private String name;
    private DataType dataTypes;
    private Boolean nullable;
    private Metadata metadata;

    SaleSchema(String name, DataType dataTypes, Boolean nullable, Metadata metadata) {

        this.name = name;
        this.dataTypes = dataTypes;
        this.nullable = nullable;
        this.metadata = metadata;
    }

    public static StructType getSchema() {

        int schemaSize = values().length;
        StructField[] fields = new StructField[schemaSize];
        SaleSchema[] schemaFields = values();

        for(int i = 0; i < schemaSize; i++) {
            fields[i] = new StructField(
                    schemaFields[i].name,
                    schemaFields[i].dataTypes,
                    schemaFields[i].nullable,
                    schemaFields[i].metadata);
        }

        return new StructType(fields);
    }


}
