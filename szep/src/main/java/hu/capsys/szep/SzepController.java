package hu.capsys.szep;

import hu.capsys.szep.api.SzepControllerApi;
import hu.capsys.szep.api.model.UnitDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
public class SzepController implements SzepControllerApi {

    @Override
    public Mono<ResponseEntity<Flux<UnitDto>>> _getUnits(String xClientTraceID, ServerWebExchange exchange) {
        return Mono.just(
                ResponseEntity
                        .ok()
                        .body(
                                Flux.just(
                                        UnitDto.builder()
                                                .eic("15WAJKAPV----PPK")
                                                .name("Ajka naperomu")
                                                .shortName("AJKAPVuz")
                                                .type("PRODUCER")
                                                .build(),
                                        UnitDto.builder()
                                                .eic("15WEMASZ----DSO2")
                                                .name("ÉMÁSZ")
                                                .shortName("EMASZuz")
                                                .type("DSO")
                                                .build(),
                                        UnitDto.builder()
                                                .eic("15WOAMUZ------FP")
                                                .name("Ózdi Acélmuvek")
                                                .shortName("OAMuz")
                                                .type("CONSUMER")
                                                .build()
                                )
                        )
        );
    }
}
