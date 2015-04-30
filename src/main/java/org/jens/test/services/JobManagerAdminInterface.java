package org.jens.test.services;

import java.util.List;

/**
 * Interface für alle Admin-Zugänge
 *
 * @author Jens Ritter on 29.04.15.
 */
public interface JobManagerAdminInterface<T> {
    T createJob(String username, int kredi);

    T dropJob(String id);

    List<T> list();

    int getJobCount();
}
