package org.jens.test.services;


import org.jens.test.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jens Ritter on 29.04.15.
 */
@Component
@ManagedResource(
    objectName="org.jens.test:name=JobManagerBean",
    description="JobManager"
    )
public class JmxBean implements JobManagerAdminInterface<String> {

    private final Logger logger = LoggerFactory.getLogger(JmxBean.class);

    @Resource
    JobManagerInterface jobManager;


    @ManagedOperation(description = "Create a new Job")
    @ManagedOperationParameters({
        @ManagedOperationParameter(name="username", description = "Name of User"),
        @ManagedOperationParameter(name="kredi", description = "KrediNummer")
    })
    @Override
    public String createJob(String username, int kredi) {
        Job job = new Job();
        job.setKredi(kredi);
        job.setName(username);
        logger.info("Created {}", job);
        return jobManager.addJob(job).getId();
    }

    @Nullable
    @ManagedOperation(description = "Drop a job by id")
    @ManagedOperationParameters(@ManagedOperationParameter(name = "jobid", description = "Unique jobid"))
    @Override
    public String dropJob(String id) {
        Job job=jobManager.removeJobById(id);
        if (job != null) {
            return job.getId();
        }
        return null;
    }

    @ManagedOperation(description="List all jobs")
    @Override
    public List<String> list() {
        List<String> result = new LinkedList<>();
        for(Job job : jobManager.list()) {
            result.add(job.getId());
        }
        return result;
    }

    @ManagedAttribute(description="Jobs in Queue")
    @Override
    public int getJobCount() {
        return jobManager.list().size();
    }





}
