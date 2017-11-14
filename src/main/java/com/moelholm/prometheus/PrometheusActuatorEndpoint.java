package com.moelholm.prometheus;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.actuate.endpoint.mvc.AbstractMvcEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

class PrometheusActuatorEndpoint extends AbstractMvcEndpoint {

  PrometheusActuatorEndpoint(String path, boolean sensitive) {
    super(path, sensitive);
  }

  @ResponseBody
  @RequestMapping(produces = TextFormat.CONTENT_TYPE_004)
  void writeMetrics(HttpServletResponse response) throws IOException {
    List<Collector.MetricFamilySamples> metricsList =
        Collections.list(CollectorRegistry.defaultRegistry.metricFamilySamples())
            .stream()
            .distinct()
            .collect(Collectors.toList());
    try (Writer writer = response.getWriter()) {
      TextFormat.write004(writer, Collections.enumeration(metricsList));
    }
  }
}
