package org.clxmm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.clxmm.service.shiro.mapper")
public class Shiro11springbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Shiro11springbootApplication.class, args);
    }

}
