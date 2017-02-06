package com.moelholm.prometheus;

import io.prometheus.client.spring.boot.SpringBootMetricsCollector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.endpoint.mvc.AbstractMvcEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
@ConditionalOnWebApplication
public class PrometheusAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "prometheusActuatorEndpoint")
  AbstractMvcEndpoint prometheusActuatorEndpoint(
      @Value("${endpoints.prometheus.path:/prometheus}") String path,
      @Value("${endpoints.prometheus.sensitive:false}") boolean sensitive) {
    return new PrometheusActuatorEndpoint(path, sensitive);
  }

  @Bean
  @ConditionalOnMissingBean(SpringBootMetricsCollector.class)
  SpringBootMetricsCollector springBootMetricsCollector(Collection<PublicMetrics> publicMetrics) {
    SpringBootMetricsCollector collector = new SpringBootMetricsCollector(publicMetrics);
    collector.register();
    return collector;
  }

}