package com.art.xuptbbs;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class XuptbbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuptbbsApplication.class, args);
    }

}
