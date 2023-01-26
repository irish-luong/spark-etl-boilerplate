package com.max.etl.boilerplate.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExceptionHandler {

    public void handleException(Exception exceptionThrow) {
        log.error("Non Business Exception Occurred : " + exceptionThrow.getMessage());
    }
}
