//package com.sg.fitness.gateway.filter;
//
//import com.nimbusds.jwt.JWTClaimsSet;
//import com.nimbusds.jwt.SignedJWT;
//import com.sg.fitness.gateway.dto.RegisterRequest;
//import com.sg.fitness.gateway.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//import java.util.Objects;
//
//
///**
// * A global web filter that synchronizes incoming user identity information with
// * the downstream user service before the request continues through the gateway.
// *
// * <p>This filter reads the request headers and Keycloak JWT claims, checks
// * whether the user already exists in the user service, and registers the user
// * when needed. In simple terms, it makes sure the gateway and the user service
// * stay in sync before forwarding the request to the rest of the system.</p>
// */
//@Component
//@Slf4j
//public class KeycloakUserSyncFilter implements WebFilter {
//
//    private final Logger logger = LoggerFactory.getLogger(KeycloakUserSyncFilter.class);
//    @Autowired
//    UserService userService;
//
////    Taken by implementing org.springframework.cloud.gateway.filter.GlobalFilter
////    @Override
////    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
////        logger.info("Path of the request received -> {}", exchange.getRequest().getPath());
////        return chain.filter(exchange);
////    }
//
//    /**
//     * Runs for every incoming request that reaches the gateway and ensures the
//     * user represented by the JWT and request headers is synchronized with the
//     * downstream user service.
//     *
//     * <p>In simple terms, this filter first checks whether the request already
//     * contains a user identifier. If not, it tries to derive the user identity
//     * from the Keycloak JWT claims. Then it asks the user service if the user
//     * already exists. If the user is missing, the filter registers the user and
//     * only after that continues the original request with the user ID attached to
//     * the request headers so downstream services can identify the caller.</p>
//     *
//     * @param exchange the current server web exchange containing request and response
//     * @param chain    the filter chain that continues the request flow
//     * @return a reactive completion signal after the sync logic and the original
//     * request are handled
//     */
////    @Override
////    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
////        String path = exchange.getRequest().getPath().value();
////        log.info("Incoming path = {}", path);
////        if (path.startsWith("/actuator")) {
////            return chain.filter(exchange);
////        }
////
////        String userId = exchange.getRequest().getHeaders().getFirst("X-User-ID");
////        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
////        RegisterRequest registerRequest = getUserDetails(token);
////
////        if (token == null || token.isBlank()) {
////            log.info("Forwarding request: {}", exchange.getRequest().getURI());
////            return chain.filter(exchange);
////        }
////
////        if (userId == null && registerRequest != null) {
////            userId = registerRequest.getKeycloakId();
////        }
////
////        if (userId != null && token != null) {
////            String finalUserId = userId;
////            return userService.validateUser(userId)
////                    .flatMap(exist -> {
////                        if (!exist) {
////                            // The user is not present in the downstream system, so create it first.
////                            if (registerRequest != null) {
////                                return userService.registerUser(registerRequest)
////                                        .then(Mono.empty());
////                            } else {
////                                return Mono.empty();
////                            }
////                        } else {
////                            logger.info("User already exist, Skipping sync.");
////                            return Mono.empty();
////                        }
////                    })
////                    // defer = it will only execute when above portion execution is completed. It won't start otherwise
////                    .then(Mono.defer(() -> {
////                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
////                                .header("X-User-ID", finalUserId)
////                                .build();
////                        log.info("Forwarding request: {}", exchange.getRequest().getURI());
////                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
////                    }));
////        }
////        log.info("Forwarding request: {}", exchange.getRequest().getURI());
////        return chain.filter(exchange);
////    }
////
////    /**
////     * Extracts the user information from the incoming bearer token.
////     *
////     * <p>The method removes the "Bearer " prefix, parses the JWT, and reads the
////     * claims from the token. These claims are then used to build a registration
////     * payload that can be forwarded to the user service. In short, this is the
////     * place where the gateway turns an incoming access token into a user object
////     * that can be saved in the downstream service.</p>
////     *
////     * @param token the authorization header value, usually in the form
////     *              "Bearer <jwt>"
////     * @return a registration request generated from the JWT claims, or null when
////     * the token cannot be parsed
////     */
////    private RegisterRequest getUserDetails(String token) {
////        try {
////            String tokenWithoutBearer = token.replace("Bearer ", "").trim();
////            SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
////            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
////            // The token claims are used as the main identity source; a placeholder email is set for the registration payload.
////            return new RegisterRequest(claims, "dummy@123123");
////
////        } catch (Exception exception) {
////            String message = "GATEWAY-SERVICE:: " + exception.getClass().getSimpleName() + ": " + exception.getMessage();
////            logger.error("getUserDetails Exception occurred: {}", message);
////            return null;
////        }
////    }
//}