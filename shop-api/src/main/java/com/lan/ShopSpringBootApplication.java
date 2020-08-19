package com.lan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.lan.mapper")
public class ShopSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopSpringBootApplication.class,args);
    }
}
