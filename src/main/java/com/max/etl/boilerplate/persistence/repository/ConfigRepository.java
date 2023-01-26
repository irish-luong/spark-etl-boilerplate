package com.max.etl.boilerplate.persistence.repository;

import com.max.etl.boilerplate.persistence.entity.JobConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends CrudRepository<JobConfiguration, Integer> {

    JobConfiguration findTop1ByJobNameAndClientIdAndConfigName(String jobName, String clientId, String configName);
}
