package dev.antoniogrillo.primoesempiocineca.service.jpaimpl;

import dev.antoniogrillo.primoesempiocineca.model.Utente;
import dev.antoniogrillo.primoesempiocineca.repository.jparepo.UtenteRepository;
import dev.antoniogrillo.primoesempiocineca.service.def.GestoreTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class GestoreTokenImpl implements GestoreTokenService {

    private final UtenteRepository repo;

    @Value("${mio.custom.tag.key}")
    private String key;

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }



    @Override
    public String generaToken(Utente utente) {
        return Jwts.builder()
                .claims()
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + (1000L * 60 * 60)))
                    .subject(utente.getUsername())
                    .add("ruolo", utente.getRuolo().name())
                .and()
                .signWith(getKey())
                .compact();
    }

    @Override
    public boolean verificaToken(String token) {
        try{
            Claims claims=getClaims(token);
            if(claims==null)return false;
            return !claims.getExpiration().before(new Date());
        }catch (JwtException e){
            return false;
        }
    }

    @Override
    public Utente getUtenteDaToken(String token) {
        if(verificaToken(token)){
            String username=getUsername(token);
            return repo.findByEmail(username).orElse(null);
        }
        return null;
    }

    private Claims getClaims(String token){
        if(token==null)return null;
        if(token.startsWith("Bearer "))token=token.substring(7);
        return (Claims) Jwts.parser().verifyWith(getKey()).build()
                .parse(token)
                .getPayload();
    }

    private String getUsername(String token){
        return getClaims(token).getSubject();
    }
}
