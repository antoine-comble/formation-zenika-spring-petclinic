package com.petclinic.petclinic.practice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingServiceTest {

    @Test
    public void shouldGreetSuccessfully() {
        // Given
        GreetingService greetingService = new GreetingService();
        // When
        String hello = greetingService.sayHi();
        // Then
        assertThat(hello).isEqualTo("Hello");
    }
}
