package com.testone.customeraccounts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CustomeraccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomeraccountsApplication.class, args);
        log.info("Go to http://localhost:8081/swagger-ui/index.html");
    }

}
