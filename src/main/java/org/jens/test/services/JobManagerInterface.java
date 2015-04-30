package org.jens.test.services;

import org.jens.test.model.Job;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Jens Ritter on 29.04.15.
 */
public interface JobManagerInterface {

    Job addJob(@Nonnull Job job);

    List<Job> list();

    @Nullable
    Job getWork();

    Job getJobById(String id);

    Job removeJobById(String id);
}
