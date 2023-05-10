package hu.capsys.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        return http
//                .authorizeExchange(exchange ->
//                        exchange.matchers(EndpointRequest.toAnyEndpoint())
//                                .permitAll()
//                                .pathMatchers("/api/szep/units/auth").authenticated()
//                                .anyExchange()
//                                .permitAll()
//                )
//                .oauth2Login(Customizer.withDefaults())
//                .build();
//    }


    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just("user");
    }

}
