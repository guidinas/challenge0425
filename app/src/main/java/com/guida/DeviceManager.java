package com.guida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication (scanBasePackages =  {"com.guida"} )
public class DeviceManager {
    public static void main(String[] args) {
        SpringApplication.run(DeviceManager.class, args);
    }
}