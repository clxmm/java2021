package org.clxmm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.clxmm.service.shiro.mapper")
public class Shiro12springbootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(Shiro12springbootJwtApplication.class, args);
    }

}
