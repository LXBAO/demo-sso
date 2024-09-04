package com.pub.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages={"com.pub.auth","com.common"})
@MapperScan({"com.common.mapper"})
@EnableDiscoveryClient
public class SpringAuthApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(SpringAuthApplication.class, args);
        }catch (Throwable e){
           System.out.println(e);
        }

    }

}
