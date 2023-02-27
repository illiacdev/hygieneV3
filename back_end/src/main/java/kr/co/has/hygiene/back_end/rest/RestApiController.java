package kr.co.has.hygiene.back_end.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @PostMapping("/upload")
    Mono<String> upload(
            @RequestPart(value = "file") MultipartFile multipartFile
    ) {

        return Mono.just("OK!");
    }
    @PostMapping("/upload2")
    public Mono<String>  uploadFile(
//            @RequestParam(value="file") MultipartFile multipartFile
            @RequestPart(value="file") MultipartFile[] multipartFile
//            @RequestParam("file") MultipartFile file
//             @RequestParam("files") List<MultipartFile> files
    ) throws IOException {
//        fileService.saveFile(file);

        /*for (MultipartFile multipartFile : files) {
            fileService.saveFile(multipartFile);
        }*/

        return Mono.just("OK!");
    }

}
