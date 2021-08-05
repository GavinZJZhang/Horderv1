package org.hzero.admin;

import org.hzero.autoconfigure.admin.EnableHZeroAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroAdmin
@EnableDiscoveryClient
@SpringBootApplication

public class HandadminsaasApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandadminsaasApplication.class, args);
        }

}