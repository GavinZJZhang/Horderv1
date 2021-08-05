package org.hzero.workflow;

import org.hzero.autoconfigure.EnableHzeroWorkflow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHzeroWorkflow
@EnableDiscoveryClient
@SpringBootApplication

public class HandworkflowApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandworkflowApplication.class, args);
        }

}