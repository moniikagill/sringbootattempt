package com.test.springboot

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.test.context.ContextConfiguration

@ConfigurationProperties(prefix = "test")
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@EnableConfigurationProperties
class TestMain {
    Map Test1
    int num1
    int num2
    int finalResult

    Map getTest1() {
        return Test1
    }

    void setTest1(Map test1) {
        Test1 = test1
    }
    @Given('^number (.+) and number (.+)$')
    void giventest(int x, int y){


        Map t = Test1
        println(t)
        println("------------------")
        num1 = x
        num2 = y
        // Write code here that turns the phrase above into concrete actions
       // throw new PendingException()
    }



    @When('^an (.+) is performed$')
    void whenperf(String y){

        if(y == "addition") {
            finalResult = num1 + num2
        }


    }


    @Then('^the expected (.+) is returned$')
    void thenans(int result){
       assert finalResult == result : "something wrong happened"
    }



}
