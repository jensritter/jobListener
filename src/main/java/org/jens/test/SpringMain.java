package org.jens.test;

import org.jens.shorthand.web.EmbeddedJetty;
import org.jens.shorthand.web.EmbeddedJettyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jens Ritter on 30.04.15.
 */
@Service
public class SpringMain implements CommandLineRunner, MainInterface {

    private static final Logger LOG = LoggerFactory.getLogger(SpringMain.class);

    /**
     * SpringBoot-Config
     */
    @SpringBootApplication
    @ImportResource("classpath:/org/jens/test/context.xml")
    public static class Config {
        @Bean
        public SpringMain springMain() {
            return new SpringMain();
        }
    }

    /**
     * SpringBoot-Init
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Config.class);
        app.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        try(ConfigurableApplicationContext ctx = app.run(args)) {
            LOG.info("Bye.");
        }
    }

    @Resource
    EmbeddedJettyConfig cfg;

    @Resource
    ApplicationContext ctx;

    @Resource
    PropertySourcesPlaceholderConfigurer psPc;

    /**
     * Main
     *
     * @param args
     */
    @Override
    public void run(String... args) {
        PropertySource<?> sources = psPc.getAppliedPropertySources().get("environmentProperties");
        LOG.info("");
        LOG.info("SSH-Enabled  : {}", sources.getProperty("shell.ssh.enabled"));
        LOG.info("SSH-Port     : {}", sources.getProperty("shell.ssh.port"));
        LOG.info("SSH-Username : {}", sources.getProperty("shell.auth.simple.user.name"));
        LOG.info("");

        LOG.info("Admin-Rest-Interface : http://127.0.0.1:{}/swagger", cfg.getPort());
        LOG.info("");

        // environmentProperties
        /*
        System.out.println("BeanDefinitions:");
        List<String> lst = new ArrayList<>();
        for(String name : ctx.getBeanDefinitionNames()) {
            Class<?> bean = ctx.getBean(name).getClass();
            lst.add(bean.getName() + " (" + name + ")");
        }
        Collections.sort(lst);
        for(String it : lst) {
            System.out.println(it);
        }

        System.out.println("");
        */
        EmbeddedJetty jetty = new EmbeddedJetty(cfg);
        cfg.setParentContext(ctx);
        try {
            jetty.start();
            while(keepRunning) {
                Thread.sleep(1_000);
            }
            LOG.info("Shutting down");
            jetty.stop();
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private boolean keepRunning = true;

    @Override
    public void setKeepRunning(boolean value) {
        keepRunning = value;
    }


}
