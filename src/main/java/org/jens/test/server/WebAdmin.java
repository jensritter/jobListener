package org.jens.test.server;

import com.wordnik.swagger.annotations.Api;
import org.jens.test.MainInterface;
import org.jens.test.model.Job;
import org.jens.test.services.JobManagerAdminInterface;
import org.jens.test.services.JobManagerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jens Ritter on 29.04.15.
 */
@RestController
@Api(value="Rest-Methods", hidden = false)
public class WebAdmin implements JobManagerAdminInterface<Job> {

    private final Logger logger = LoggerFactory.getLogger(WebAdmin.class);

    public static final String RESP_OK="OK";
    public static final String RESP_ERR="ERROR";


    @Resource
    JobManagerInterface jobManager;

    @Resource
    MainInterface main;

    @RequestMapping(method = RequestMethod.GET, value = "/start")
    @Override
    public Job createJob(@RequestParam("user") String username, @RequestParam("kredi")int kredi) {
        logger.info("adding {} with {}", username, kredi);
        Job job = new Job();
        job.setKredi(kredi);
        job.setName(username);
        jobManager.addJob(job);
        return job;
    }

    @Override
    public Job dropJob(String id) {
        return jobManager.removeJobById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value="/list")
    @Override
    public List<Job> list() {
        return jobManager.list();
    }


    @Override
    public int getJobCount() {
        return jobManager.list().size();
    }

    @RequestMapping(method = RequestMethod.GET, value="/jetty/shutdown")
    public String shutdownJetty() {
        main.setKeepRunning(false);
        return RESP_OK;
    }


}
