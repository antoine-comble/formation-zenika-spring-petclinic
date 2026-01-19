package com.petclinic.petclinic.practice;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingServiceTest {

    AnnotationConfigApplicationContext applicationContext;
    GreetingService greetingService;

    public GreetingServiceTest() {
        applicationContext = new AnnotationConfigApplicationContext("com.petclinic.petclinic.practice");
        greetingService = applicationContext.getBean(GreetingService.class);
    }

    @Test
    public void shouldGreetSuccessfully() {
        // Given
        GreetingService greetingService = new GreetingService();
        // When
        String hello = greetingService.sayHi();
        // Then
        assertThat(hello).isEqualTo("Hello");
    }

    @Test
    public void shouldGreetSuccessfullyUsingInjection() {
        // When
        String hello = greetingService.sayHi();
        // Then
        assertThat(hello).isEqualTo("Hello");
    }

    @Test
    public void shouldCountBeanDefinitions() {
        // When
        int count = applicationContext.getBeanDefinitionCount();
        // Then
        assertThat(count).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void shouldDisplayBeanDefinitions() {
        // When
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        // Then
        Stream<String> beanDefinitionNamesAsStream = Arrays.stream(beanDefinitionNames);
        beanDefinitionNamesAsStream.forEach(System.out::println);
        long greetingserviceInstanceCount = beanDefinitionNamesAsStream.filter(s -> s.toLowerCase().contains("greetingservice")).count();
        // Then
        assertThat(greetingserviceInstanceCount).isEqualTo(1);
    }
}
