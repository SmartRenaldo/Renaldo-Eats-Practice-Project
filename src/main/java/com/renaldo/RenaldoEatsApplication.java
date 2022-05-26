package com.renaldo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RenaldoEatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenaldoEatsApplication.class, args);
    }

}
