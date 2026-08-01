package com.sg.fitness.userservice.config;

import io.micrometer.tracing.exporter.SpanExportingPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    @Bean
    SpanExportingPredicate noEurekaSpans() {
        return span -> {
            String url = span.getTags().get("http.url");
            if (url == null) url = span.getTags().get("url.full");
            boolean isEureka = url != null && (url.contains("/eureka") || url.contains(":8761"));
            return !isEureka;   // false = drop
        };
    }

//    @Value("${OTEL_EXPORTER_OTLP_ENDPOINT:http://localhost:4318}")
//    String tracingEndpoint;
//
//    @Bean
//    public SpanExporter otlpHttpSpanExporter() {
//        OtlpHttpSpanExporter delegate = OtlpHttpSpanExporter.builder()
//                .setEndpoint(tracingEndpoint + "/v1/traces")
//                .build();
//
//        return new SpanExporter() {
//            @Override
//            public CompletableResultCode export(Collection<SpanData> spans) {
//                var kept = spans.stream().filter(s -> {
//                    String u = s.getAttributes().get(AttributeKey.stringKey("url.full"));
//                    if (u == null) {
//                        u = s.getAttributes().get(AttributeKey.stringKey("http.url"));
//                    }
//                    return u == null || !(!u.contains("/eureka") || u.contains(":8761"));
//                }).toList();
//                return kept.isEmpty() ? CompletableResultCode.ofSuccess() : delegate.export(kept);
//            }
//
//            @Override
//            public CompletableResultCode flush() {
//                return delegate.flush();
//            }
//
//            @Override
//            public CompletableResultCode shutdown() {
//                return delegate.shutdown();
//            }
//        };
//    }

//    @PostConstruct
//    void setupHook() {
//        reactor.core.publisher.Hooks.enableAutomaticContextPropagation();
//    }

//    private boolean isEureka(String url) {
//        if (url == null) return false;
//        return url.contains("/eureka") || url.contains(":8761") || url.contains("/registry/eureka");
//    }

}
