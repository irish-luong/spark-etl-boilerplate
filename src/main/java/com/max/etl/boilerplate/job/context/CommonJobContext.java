package com.max.etl.boilerplate.job.context;


import com.max.etl.boilerplate.parameter.Parameters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public abstract class CommonJobContext {

    @Autowired
    private Parameters parameters;

    @Value("${spark.master.mode}")
    private String sparkMode;

}
