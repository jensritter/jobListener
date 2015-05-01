package org.jens.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.ImportResource;

/**
 * SpringBoot-Config
 */
@SpringBootApplication
@EnableMBeanExport
@ImportResource("classpath:/org/jens/test/context.xml")
@ConfigurationProperties("jobmanager.properties")
public class JobListenerConfig {
    @Bean
    public JobListenerApplication jobListenerApplication() {
        return new JobListenerApplication();
    }
}
