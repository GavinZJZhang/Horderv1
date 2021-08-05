package org.hzero.report;

;
import org.hzero.autoconfigure.report.EnableHZeroReport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableHZeroReport
@EnableDiscoveryClient
@SpringBootApplication

public class HandreportsaasApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(HandreportsaasApplication.class, args);
        }

}