package com.petclinic.petclinic.practice;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingServiceTest {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    final AnnotationConfigApplicationContext applicationContext;
    final GreetingService greetingService;

    public GreetingServiceTest() {
        applicationContext = new AnnotationConfigApplicationContext("com.petclinic.petclinic.practice");
        greetingService = applicationContext.getBean(GreetingService.class);
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
