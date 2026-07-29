//package com.sg.fitness.gateway.config;
//
//import io.opentelemetry.api.common.AttributeKey;
//import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
//import io.opentelemetry.sdk.common.CompletableResultCode;
//import io.opentelemetry.sdk.trace.data.SpanData;
//import io.opentelemetry.sdk.trace.export.SpanExporter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Collection;
//
//@Configuration
//public class TracingConfig {
//
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
//
////    @PostConstruct
////    void setupHook() {
////        reactor.core.publisher.Hooks.enableAutomaticContextPropagation();
////    }
//
////    SpanExportingPredicate noEurekaSpans() {
////        return span -> {
////            String url = span.getTags().get("url.full");
////            if (url == null) url = span.getTags().get("http.url");
////            return !isEureka(url);
////        };
////    }
//
////    private boolean isEureka(String url) {
////        if (url == null) return false;
////        return url.contains("/eureka") || url.contains(":8761") || url.contains("/registry/eureka");
////    }
//
//}
