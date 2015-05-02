package org.jens.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.ImportResource;

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

    @Bean
    public String config(@Value("${joblistener.message}")String msg) {
        return msg;
    }

}
