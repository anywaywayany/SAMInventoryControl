package com.samic.samic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestContainerConfiguration.class)
@ComponentScan(basePackages = {"com.samic.samic.data.persistence"})
public class TestApplication {

    public static void main(String[] args){
        SpringApplication.from(Application::main)
                .with(TestContainerConfiguration.class)
                .run(args);
    }
}
