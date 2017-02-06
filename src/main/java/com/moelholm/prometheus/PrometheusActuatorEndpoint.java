package com.moelholm.prometheus;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.springframework.boot.actuate.endpoint.mvc.AbstractMvcEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.Writer;
import javax.servlet.http.HttpServletResponse;

class PrometheusActuatorEndpoint extends AbstractMvcEndpoint {

  PrometheusActuatorEndpoint(String path, boolean sensitive) {
    super(path, sensitive);
  }

  @ResponseBody
  @RequestMapping(produces = TextFormat.CONTENT_TYPE_004)
  void writeMetrics(HttpServletResponse response) throws IOException {
    try (Writer writer = response.getWriter()) {
      TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
    }
  }

}
