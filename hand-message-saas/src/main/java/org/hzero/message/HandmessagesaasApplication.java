package org.hzero.message;

import org.hzero.autoconfigure.message.EnableHZeroMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroMessage
@EnableDiscoveryClient
@SpringBootApplication

public class HandmessagesaasApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandmessagesaasApplication.class, args);
        }

}