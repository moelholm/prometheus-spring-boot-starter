package com.moelholm.prometheus;

import com.google.common.collect.Iterators;
import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.springframework.boot.actuate.endpoint.mvc.AbstractMvcEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Iterator;

class PrometheusActuatorEndpoint extends AbstractMvcEndpoint {

    PrometheusActuatorEndpoint(String path, boolean sensitive) {
        super(path, sensitive);
    }

    @ResponseBody
    @RequestMapping(produces = TextFormat.CONTENT_TYPE_004)
    void writeMetrics(HttpServletResponse response) throws IOException {
        try (Writer writer = response.getWriter()) {
            Iterator<Collector.MetricFamilySamples> iterator = Collections.list(CollectorRegistry.defaultRegistry.metricFamilySamples()).stream().distinct().iterator();
            TextFormat.write004(writer, Iterators.asEnumeration(iterator));
        }
    }

}
