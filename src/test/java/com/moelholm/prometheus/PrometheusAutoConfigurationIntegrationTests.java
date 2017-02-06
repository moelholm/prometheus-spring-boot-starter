package com.moelholm.prometheus;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = PrometheusAutoConfigurationIntegrationTests.TestConfig.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "management.port=0")
public class PrometheusAutoConfigurationIntegrationTests {

  @Value("${local.management.port}")
  private int managementPort;

  @Test
  public void prometheusAutoConfiguration_whenActive_thenRegistersActuatorEndpoint() {

    // Given
    RestTemplate restTemplate = new RestTemplate();

    // When
    ResponseEntity<String> entity = restTemplate
        .getForEntity("http://localhost:{port}/prometheus", String.class, managementPort);

    // Then
    assertThat(entity.getStatusCodeValue()).isEqualTo(200);
    assertThat(entity.getBody()).contains("# HELP heap_used heap_used");
    assertThat(entity.getBody()).contains("# TYPE heap_used gauge");

  }

  @SpringBootApplication
  static class TestConfig {
  }

}