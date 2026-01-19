package com.petclinic.petclinic.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GreetingServiceTest {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    final AnnotationConfigApplicationContext applicationContext;
    final GreetingService greetingService;

    @Autowired
    public GreetingServiceTest(GreetingService greetingService) {
        this.applicationContext = new AnnotationConfigApplicationContext("com.petclinic.petclinic.practice");
        this.greetingService = greetingService;
    }

    @Test
    public void shouldGreetSuccessfully() {
        // Given
        final GreetingService greetingService = new GreetingService();
        // When
        final String hello = greetingService.sayHi();
        // Then
        assertThat(hello).isEqualTo("Hello");
    }

    @Test
    public void shouldGreetSuccessfullyUsingInjection() {
        // When
        final String hello = greetingService.sayHi();
        // Then
        assertThat(hello).isEqualTo("Hello");
    }

    @Test
    public void shouldCountBeanDefinitions() {
        // When
        final int count = applicationContext.getBeanDefinitionCount();
        // Then
        assertThat(count).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void shouldDisplayBeanDefinitions() {
        // When
        final String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        // Then
        logger.info(String.join(",", beanDefinitionNames));
        long greetingserviceInstanceCount = Arrays.stream(beanDefinitionNames).filter(s -> s.toLowerCase().contains("greetingservice")).count();
        // Then
        assertThat(greetingserviceInstanceCount).isEqualTo(1);
    }
}
