package com.max.etl.boilerplate.job;


/**
 * Abstract base class for a customized Spark job
 */
public abstract class Job<T> {

    abstract protected T preProcess();
    abstract protected T process(T preProcessOutput);
    abstract protected void postProcess(T processOutput);

    /**
     * Method implement a standard process in ELT jobs
     */
    public void execute() {
        T preProcessOutput = preProcess();
        T processOutput = process(preProcessOutput);
        postProcess(processOutput);
    }
}
