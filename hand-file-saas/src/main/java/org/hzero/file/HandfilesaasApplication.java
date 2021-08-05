package org.hzero.file;

import org.hzero.autoconfigure.file.EnableHZeroFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroFile
@EnableDiscoveryClient
@SpringBootApplication

public class HandfilesaasApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandfilesaasApplication.class, args);
        }

}