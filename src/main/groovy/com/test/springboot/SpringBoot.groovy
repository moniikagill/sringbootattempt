package com.test.springboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationContext

@SpringBootApplication
@EnableConfigurationProperties(TestMain.class)
class SpringBoot {

    static void main(String[] args){
        final ApplicationContext ctx = SpringApplication.run(SpringBoot.class, args)
        final TestMain test = ctx.getBean(TestMain.class)
        println("tetsing-----")
        println(test.getTest1())


    }
}
