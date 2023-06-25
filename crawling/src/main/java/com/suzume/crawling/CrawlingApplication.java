package com.suzume.crawling;

import com.suzume.crawling.service.CrawlingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlingApplication.class, args).getBean(CrawlingService.class).crawlingData();
    }

}
