package kr.co.has.hygiene.back_end.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class RestApiController {
    final RestService service;

    @PostMapping("/upload")
    Mono<String> upload(
            @RequestPart(value = "file") Mono<FilePart> file
    ) {


        return Mono.create(stringMonoSink -> {
            file.subscribe(filePart -> {

                filePart.content()
                        .map(dataBuffer -> dataBuffer.asInputStream(true)).map(inputStream -> {
                            return service.makeTree(filePart.filename(), inputStream);

                        }).subscribe(node -> {
                            stringMonoSink.success(node.toString());
                        });


            }, throwable -> {
                stringMonoSink.error(throwable);
            });
        });
    }

}
