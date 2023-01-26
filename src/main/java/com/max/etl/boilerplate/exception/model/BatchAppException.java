package com.max.etl.boilerplate.exception.model;

public class BatchAppException extends RuntimeException {

    public BatchAppException(String msg) {
        super(msg);
    }

    public BatchAppException(String msg, Throwable ex) {
        super(msg, ex);
    }

}
