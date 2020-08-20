package com.lan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.lan.mapper")
@ComponentScan(value = {"com.lan","org.n3r.idworker"})
public class ShopSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopSpringBootApplication.class, args);
    }

}