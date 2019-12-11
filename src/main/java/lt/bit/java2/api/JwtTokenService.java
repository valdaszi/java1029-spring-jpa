package lt.bit.java2.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JwtTokenService {

    private Algorithm algorithm; // = Algorithm.HMAC256("Labai slaptas raktas");

    // Jei nurodyta ${name} tai bus naudojama parametro 'name' reiksme is application.properties
    @Value("${secret.key}")
    private String secretKey;

    @PostConstruct
    void init() {
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    public String build(UserDetails userDetails) {
        long date = new Date().getTime() + 2 * 60 * 60 * 1000;
        Date dateTo = new Date(date);

        String[] authorities = userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(Collectors.toList())
                .toArray(new String[] {});

        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withIssuer("Bit Java2")
                .withExpiresAt(dateTo)
                .withClaim("name", userDetails.getUsername())
                .withArrayClaim("authorities", authorities)
                .sign(algorithm);
    }

    boolean isTokenValid(String token) {
        return false;
    }
}
