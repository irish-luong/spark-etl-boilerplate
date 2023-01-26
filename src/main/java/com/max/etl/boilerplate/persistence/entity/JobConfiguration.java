package com.max.etl.boilerplate.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "job_config")
public class JobConfiguration {

    public JobConfiguration() {};

    @Id @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "config_name")
    private String configName;

    @Column(name = "`value`")
    private String value;
}
