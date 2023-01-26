package com.max.etl.boilerplate;

import com.max.etl.boilerplate.job.Job;
import com.max.etl.boilerplate.parameter.Parameters;
import com.max.etl.boilerplate.exception.ExceptionHandler;
import com.max.etl.boilerplate.parameter.CommonJobParameter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class SparkBatchApp implements CommandLineRunner {

    @Autowired
    private Parameters parameters;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ExceptionHandler exceptionHandler;

    public static void main (String[] args) {

        log.info("Start batching application!");
        SpringApplication.run(SparkBatchApp.class, args);
        log.info("Batch application finished");

    }

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("Parse common job arguments ........");
            // Load common job parameters
            loadCommonParameters(args);
            log.info("Instantiating job ...........");
            Job job = (Job) applicationContext.getBean(
                    parameters.getParameterValue(CommonJobParameter.JOB_NAME),
                    CommonJobParameter.JOB_NAME);

            log.info("Executing job ...........");
            job.execute();
        } catch (Exception e) {
            exceptionHandler.handleException(e);
            throw e;
        }
    }

    private void loadCommonParameters(String[] args) {
        parameters.loadAllParams(args, CommonJobParameter.values());
    }


}
