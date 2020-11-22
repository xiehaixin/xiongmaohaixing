package com.xiongmaohaixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@EnableAsync
@EnableScheduling   // 2.开启定时任务
@SpringBootApplication
public class XiongmaohaixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiongmaohaixinApplication.class, args);
    }

}
