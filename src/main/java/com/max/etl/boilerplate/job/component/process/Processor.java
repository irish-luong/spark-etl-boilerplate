package com.max.etl.boilerplate.job.component.process;

public interface Processor<T> {

    T process(T input);
}
