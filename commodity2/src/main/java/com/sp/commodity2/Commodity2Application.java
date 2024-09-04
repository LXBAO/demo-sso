package com.sp.commodity2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages={"com.sp.commodity2"})
@MapperScan({"com.sp.commodity2.mapper"})
@EnableFeignClients
@EnableDiscoveryClient
public class Commodity2Application {

  public static void main(String[] args) {
    SpringApplication.run(Commodity2Application.class, args);
  }

}

