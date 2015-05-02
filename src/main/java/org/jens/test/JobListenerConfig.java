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
public class JobListenerConfig {

    @Bean
    public JobListenerApplication jobListenerApplication() {
        return new JobListenerApplication();
    }


//
//    <bean id="jettyConfig" class="org.jens.shorthand.web.EmbeddedJettyConfig">
//    <property name="port" value="${joblistener.rest.port}"/>
//    <property name="springConfig" value="classpath:/org/jens/test/mvc-context.xml"/>
//    </bean>

    @Resource
    ApplicationContext ctx;


    @Bean
    public EmbeddedJettyConfig embeddedConfig(@Value("${joblistener.rest.port}")Integer port) {
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
