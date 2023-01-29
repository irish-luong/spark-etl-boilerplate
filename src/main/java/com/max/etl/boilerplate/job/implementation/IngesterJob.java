package com.max.etl.boilerplate.job.implementation;

import com.max.etl.boilerplate.job.Job;
import com.max.etl.boilerplate.job.component.process.Processor;
import com.max.etl.boilerplate.job.component.read.Reader;
import com.max.etl.boilerplate.job.component.write.Writer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class IngesterJob extends Job<Dataset<Row>> {


    private final Reader<Dataset<Row>> ingesterReader;
    private final Processor<Dataset<Row>> ingesterProcessor;
    private final Writer<Dataset<Row>> ingesterWriter;

    @Autowired
    IngesterJob(
            @Qualifier("ingesterReader") Reader<Dataset<Row>> ingesterReader,
            @Qualifier("ingesterProcessor") Processor<Dataset<Row>> ingesterProcessor,
            @Qualifier("ingesterWriter") Writer<Dataset<Row>> ingesterWriter ) {
        this.ingesterReader = ingesterReader;
        this.ingesterProcessor = ingesterProcessor;
        this.ingesterWriter = ingesterWriter;
    }

    /**
     * @return
     */
    @Override
    protected Dataset<Row> preProcess() {
        return ingesterReader.read();
    }

    /**
     * @param preProcessOutput
     * @return
     */
    @Override
    protected Dataset<Row> process(Dataset<Row> preProcessOutput) {
        return ingesterProcessor.process(preProcessOutput);
    }

    /**
     * @param processOutput
     */
    @Override
    protected void postProcess(Dataset<Row> processOutput) {
        ingesterWriter.write(processOutput);
    }
}
