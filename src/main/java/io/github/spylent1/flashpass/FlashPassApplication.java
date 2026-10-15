package io.github.spylent1.flashpass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("io.github.spylent1.flashpass.mapper")
@SpringBootApplication
public class FlashPassApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashPassApplication.class, args);
    }

}
