package hu.capsys.szep.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingWebFilter implements WebFilter {

    static final PathPattern PATH_PATTERN = new PathPatternParser().parse("/api/szep/**");
    final ObjectMapper mapper;

    @NonNull
    @Override
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
//        mapper.enable(INDENT_OUTPUT);

        ServerHttpRequest request = exchange.getRequest();

        if (!PATH_PATTERN.matches(request.getPath().pathWithinApplication())) {
            return chain.filter(exchange);
        }

        Map<String, Object> req = new LinkedHashMap<>();
        req.put("Uri", request.getURI());
        req.putAll(request.getHeaders());

        log(Map.of("Request", req));


        Mono<Void> filter = chain.filter(exchange);

        exchange.getResponse().beforeCommit(() -> {
            ServerHttpResponse response = exchange.getResponse();
            Map<String, Object> res = new LinkedHashMap<>();
            res.put("Status", response.getStatusCode());
            res.putAll(response.getHeaders());
            log(Map.of("Response", res));
            return Mono.empty();
        });

        return filter;
    }


    private void log(Map<String, Object> map) {
        try {
            log.info(mapper.writeValueAsString(map));
        } catch (Exception ignored) {
        }
    }
}
