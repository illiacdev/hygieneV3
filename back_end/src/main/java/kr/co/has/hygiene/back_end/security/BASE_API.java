package kr.co.has.hygiene.back_end.security;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
class Info {
    String des = "experiment 서버";
    String dateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
//    LocalDateTime dateTime2 = LocalDateTime.now();
}

@RestController
@RequestMapping("/api")
public class BASE_API {
//    @Autowired
//    SecurityContextRepository securityContextRepository;


    @GetMapping("/info")
    Mono<Object> info() {
//        return Mono.just(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return Mono.just(new Info());
    }
}
