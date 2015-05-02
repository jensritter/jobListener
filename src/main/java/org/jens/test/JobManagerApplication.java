package org.jens.test;

import org.jens.shorthand.web.EmbeddedJetty;
import org.jens.shorthand.web.EmbeddedJettyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jens Ritter on 30.04.15.
 */
@Service
public class JobManagerApplication implements CommandLineRunner, MainInterface, DisposableBean {

    private static final Logger LOG = LoggerFactory.getLogger(JobManagerApplication.class);

    /**
     * SpringBoot-Init
     *
     * @param args
     */
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(JobManagerConfig.class);
        app.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        app.setAddCommandLineProperties(true);
        try(ConfigurableApplicationContext it = app.run(args)){
            LOG.info("Bye.");
        }
    }

    @Resource
    EmbeddedJettyConfig cfg;

    @Resource
    EmbeddedJetty jetty;

    @Resource
    ApplicationContext ctx;

    @Value("${shell.ssh.enabled}")
    String sshEnabled;

    @Value("${shell.ssh.port}")
    String sshPort;

    @Value("${shell.auth.simple.user.name}")
    String sshUser;





    /**
     * Main
     *
     * @param args
     */
    @Override
    public void run(String... args) {
        LOG.info("");
        LOG.info("SSH-Enabled  : {}", sshEnabled);
        LOG.info("SSH-Port     : {}", sshPort);
        LOG.info("SSH-Username : {}", sshUser);
        LOG.info("");

        LOG.info("Admin-Rest-Interface : http://127.0.0.1:{}/swagger", cfg.getPort());
        LOG.info("");
        LOG.info("");


        try {
            //EmbeddedJetty jetty = new EmbeddedJetty(cfg);
            //cfg.setParentContext(ctx);
            jetty.start();
            LOG.info("JobListener startup complete");
            while(keepRunning) {
                Thread.sleep(500);
            }
            LOG.info("Shutting down");
            jetty.stop();
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /*

    @Override
    void run(String... args) throws Exception {
        log.info('Joining thread, you can press Ctrl+C to shutdown application')
        Thread.currentThread().join()
    }
     */
    private boolean keepRunning = true;

    @Override
    public void setKeepRunning(boolean value) {
        LOG.info("Signaling orderly shutdown");
        keepRunning = value;
    }

    /**
     * Shutdown orderly
     */
    @Override
    public void destroy()  {
        setKeepRunning(false);
    }
}

