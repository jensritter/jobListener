package org.jens.test.services;

import org.jens.test.model.Job;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Jens Ritter on 29.04.15.
 */
@Service
public class JobManager implements JobManagerInterface {

    @Resource
    CounterService counterService;

    Queue<Job> availableJobs = new LinkedBlockingQueue<>();

    @Override
    public Job addJob(@Nonnull Job job) {
        counterService.increment("info.jobs.created");
        availableJobs.add(job);
        return job;
    }

    @Override
    public List<Job> list() {
        List<Job> result = new ArrayList<>();
        result.addAll(availableJobs);
        return result;
    }

    @Nullable
    @Override
    public Job getWork() {
        return availableJobs.poll();
    }

    @Nullable
    @Override
    public Job getJobById(@Nonnull String id) {
        for(Job job : availableJobs) {
            if(job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Job removeJobById(@Nonnull String id) {
        Iterator<Job> iter = availableJobs.iterator();
        Job removed = null;
        while(iter.hasNext()) {
            Job job = iter.next();
            if(job.getId().equals(id)) {
                removed = job;
                iter.remove();
                break;
            }
        }
        return removed;
    }

}
