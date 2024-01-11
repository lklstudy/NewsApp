package com.lkl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 描述
 */

@SpringBootApplication
@MapperScan("com.lkl.mapper")
public class ActiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveApplication.class, args);
    }
}
