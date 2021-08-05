package org.hzero.scheduler;

import org.hzero.autoconfigure.scheduler.EnableHZeroScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroScheduler
@EnableDiscoveryClient
@SpringBootApplication

public class HandschedulersaasApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandschedulersaasApplication.class, args);
        }

}