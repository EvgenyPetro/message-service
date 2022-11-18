package ru.petrov.messageserver.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@Slf4j
public class JwtTokenTokenProviderJjwt implements JwtTokenProvider {

    public static final String jwtAccessSecret = "AccessSecret";

    @Override
    public User verifyAccessToken(String token) {
        return verifyToken(token);
    }

    private User verifyToken(String token) {

        Claims claims = Jwts.parser().setSigningKey(JwtTokenTokenProviderJjwt.jwtAccessSecret).parseClaimsJws(token).getBody();
        String user = claims.getSubject();

        var roles = claims.get("roles");
        var collection = new ObjectMapper()
                .convertValue(roles,
                        new TypeReference<ArrayList<HashMap<String, String>>>() {
                        });

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        collection.forEach(map -> map.values().forEach(s -> authorities.add(new SimpleGrantedAuthority(s))));

        return new User(user, "", authorities);


    }


}
