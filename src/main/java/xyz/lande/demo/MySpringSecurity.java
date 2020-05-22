package xyz.lande.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan("xyz.lande.demo.mapper")
@EnableWebSecurity
public class MySpringSecurity {
    public static void main(String[] args) {
        SpringApplication.run(MySpringSecurity.class,args);
    }
}
