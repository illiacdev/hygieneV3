package kr.co.has.hygiene.back_end.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthenticationREST {
    final AuthenticationManager authenticationManager;
    @PostMapping("/login")
    Mono<String> login(@RequestBody Map<String,String> ceidential){
        String id = ceidential.get("id");
        String password = ceidential.get("password");
        String token = String.format("Bearer %s %s", id, password);
        String return_token = String.format("%s %s", id, password);

        Mono<Authentication> authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(token, token));


        return authenticate.map(authentication -> authentication.isAuthenticated()).flatMap(aBoolean -> {

            if(aBoolean)
                return Mono.just(return_token);
            return Mono.error(new AuthenticationCredentialsNotFoundException(id));
        });
//        return String.format("%s %s",id,password);
    }

    class Response{
        String code = "OK!";
        String token;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
    @PostMapping("/login2")
    Mono<Object> login2(@RequestBody Map<String,String> ceidential){
        String id = ceidential.get("id");
        String password = ceidential.get("password");
        String token = String.format("Bearer %s %s", id, password);
        String return_token = String.format("%s %s", id, password);

        Mono<Authentication> authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(token, token));


        return authenticate.map(authentication -> authentication.isAuthenticated()).flatMap(aBoolean -> {

            Response response = new Response();
            response.setToken(return_token);

            if(aBoolean)
                return Mono.just(response);
//                return Mono.just(return_token);
            return Mono.error(new AuthenticationCredentialsNotFoundException(id));
        });
//        return String.format("%s %s",id,password);
    }

}
