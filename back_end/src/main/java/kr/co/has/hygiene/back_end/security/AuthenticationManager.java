package kr.co.has.hygiene.back_end.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String principal = (String) authentication.getPrincipal();
        String[] s = principal.split(" ");
        String Bearer = s[0];
        String id = s[1];
        String password = s[2];

        System.out.println(s);
        if (id.equals("admin") && password.equals("9999")) {
            UsernamePasswordAuthenticationToken admin = new UsernamePasswordAuthenticationToken(id, password, new ArrayList<GrantedAuthority>(List.of(new SimpleGrantedAuthority("ADMIN"))));
            return Mono.just(admin);
        }

        if (id.equals("df_22") && password.equals("9999")) {
            UsernamePasswordAuthenticationToken admin = new UsernamePasswordAuthenticationToken(id, password, new ArrayList<GrantedAuthority>(List.of(new SimpleGrantedAuthority("USER"))));
            return Mono.just(admin);
        }

        if (id.equals("user1") && password.equals("1234")) {
            UsernamePasswordAuthenticationToken admin = new UsernamePasswordAuthenticationToken(id, password, new ArrayList<GrantedAuthority>(List.of(new SimpleGrantedAuthority("USER"))));
            return Mono.just(admin);
        }


//        authentication.set
        return Mono.just(authentication);
    }


    /*@Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username = jwtUtil.getUsernameFromToken(authToken);
        return Mono.just(jwtUtil.validateToken(authToken))
            .filter(valid -> valid)
            .switchIfEmpty(Mono.empty())
            .map(valid -> {
                Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
                List<String> rolesMap = claims.get("role", List.class);
                return new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
            });
    }*/
}
