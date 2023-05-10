package hu.capsys.gateway.proxy.szep;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class SzepController {


    @GetMapping("/szep-fallback")
    public Mono<ResponseEntity<Flux<Void>>> getSzepFallback() {
        return Mono.just(
                ResponseEntity
                        .ok()
                        .body(Flux.empty())
        );
    }
}
