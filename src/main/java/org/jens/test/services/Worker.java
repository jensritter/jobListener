package org.jens.test.services;

import org.jens.test.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author Jens Ritter on 29.04.15.
 */
@Component
public class Worker {

    private final Logger logger = LoggerFactory.getLogger(Worker.class);

    @Resource
    CounterService counterService;

    @Resource
    JobManagerInterface jobManager;

    public void trigger() {
        logger.info("Trigger");

        Job job = null;
        while((job = jobManager.getWork()) != null) {

            logger.info("Handle {}", job);
            job.setFinished(LocalDateTime.now());
            counterService.increment("info.jobs.finished");
        }

    }
}
