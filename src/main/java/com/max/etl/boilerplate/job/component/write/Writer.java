package com.max.etl.boilerplate.job.component.write;

public interface Writer<T> {

    void write(T input);
}
