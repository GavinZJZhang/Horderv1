package org.hzero.interfaces;

import org.hzero.autoconfigure.interfaces.EnableHZeroInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroInterface
@EnableDiscoveryClient
@SpringBootApplication

public class HandinterfacesaasApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandinterfacesaasApplication.class, args);
        }

}