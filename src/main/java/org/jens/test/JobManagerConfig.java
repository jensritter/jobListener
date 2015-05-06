package org.jens.test;

import org.jens.shorthand.web.EmbeddedJetty;
import org.jens.shorthand.web.EmbeddedJettyConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

/**
 * SpringBoot-Config
 */
@SpringBootApplication
@EnableMBeanExport
@ImportResource("classpath:/org/jens/test/context.xml")
public class JobManagerConfig {

    @Resource
    ApplicationContext ctx;

    @Bean
    public EmbeddedJettyConfig embeddedConfig(@Value("${jobmanager.rest.port}")Integer port) {
        EmbeddedJettyConfig cfg = new EmbeddedJettyConfig();
        cfg.setPort(port);
        cfg.setSpringConfig("classpath:/org/jens/test/mvc-context.xml");
        cfg.setParentContext(ctx);
        return cfg;
    }

    @Bean
    public EmbeddedJetty embeddedJetty(EmbeddedJettyConfig cfg) {
        EmbeddedJetty jetty = new EmbeddedJetty(cfg);
        return jetty;
    }

}
