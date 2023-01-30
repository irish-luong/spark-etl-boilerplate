package com.max.etl.boilerplate.job.component.read;

import com.max.etl.boilerplate.configuration.SparkSessionProvider;
import com.max.etl.boilerplate.dictionary.IngestJobConfig;
import com.max.etl.boilerplate.job.context.CommonJobContext;
import com.max.etl.boilerplate.job.context.IngesterJobContext;
import com.max.etl.boilerplate.parameter.CommonJobParameter;
import com.max.etl.boilerplate.parameter.IngesterJobParams;
import com.max.etl.boilerplate.persistence.repository.ConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IngesterReader implements Reader<Dataset<Row>> {


    private CommonJobContext jobContext;
    private ConfigRepository configRepository;


    IngesterReader(@Qualifier("IngesterJobContext") CommonJobContext jobContext,
                   ConfigRepository configRepository ) {

        this.jobContext = jobContext;
        this.configRepository = configRepository;
    }

    /**
     * @return
     */
    @Override
    public Dataset<Row> read() {
        SparkSession sparkSession = SparkSessionProvider.provideSession(jobContext.getSparkMode());

        String fileReadFormat = jobContext.getParameters().getParameterValue(CommonJobParameter.JOB_NAME);
        String fileName = jobContext.getParameters().getParameterValue(IngesterJobParams.FILE_FORMAT);

        String fullPath = getInputPath().concat(fileName).concat(".").concat(fileReadFormat);

        DataFrameReader dataFrameReader = sparkSession.read().format(fileReadFormat);

        return getReaderFormat(dataFrameReader, fileReadFormat).load(fullPath);
    }

    /*
    Load full input path from datastore for configuration
     */
    private String getInputPath() {
        return configRepository.findTop1ByJobNameAndClientIdAndConfigName(
                jobContext.getParameters().getParameterValue(CommonJobParameter.JOB_NAME),
                jobContext.getParameters().getParameterValue(CommonJobParameter.CLIENT_ID),
                IngestJobConfig.INPUT_PATH.getConfigName()
        ).getValue();
    }

    private DataFrameReader getReaderFormat(DataFrameReader reader, String format) {

        switch (format) {
            case IngesterJobContext.CSV_FORMAT:
                return reader.option("header", "true");
            case IngesterJobContext.JSON_FORMAT:
                return reader.option("multiline", "true");
            default:
                return reader;
        }
    }
}
