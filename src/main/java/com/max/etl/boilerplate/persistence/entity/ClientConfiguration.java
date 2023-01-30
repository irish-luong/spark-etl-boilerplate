package com.max.etl.boilerplate.persistence.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "client_config")
public class ClientConfiguration {

    ClientConfiguration(){}

    @Id @GeneratedValue
    private Integer id;

    private String clientId;
    private String configName;
    private String value;

}
