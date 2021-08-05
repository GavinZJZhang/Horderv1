package org.hzero.imp;

import org.hzero.autoconfigure.imported.EnableHZeroImport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroImport
@EnableDiscoveryClient
@SpringBootApplication

public class HandimportsaasApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandimportsaasApplication.class, args);
        }

}