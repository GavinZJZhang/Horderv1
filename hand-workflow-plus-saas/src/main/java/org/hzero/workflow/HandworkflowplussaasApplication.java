package org.hzero.workflow;

import org.hzero.autoconfigure.EnableHZeroWorkflow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroWorkflow
@EnableDiscoveryClient
@SpringBootApplication

public class HandworkflowplussaasApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandworkflowplussaasApplication.class, args);
        }

}