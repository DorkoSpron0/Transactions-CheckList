package com.nicky;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class JwtService {

    private static Logger logger = Logger.getLogger(JwtService.class.getName());

    public String createToken(String username){
        try{
            Algorithm algorithm = Algorithm.HMAC256("a-string-secret-at-least-256-bits-long");
            return JWT.create()
                    .withSubject(username)
                    .withIssuer("Auth0BackendTransaction")
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + 600000)) // -> 10 minutes
                    .sign(algorithm);
        }catch (JWTCreationException ex){
            logger.warning(ex.getMessage());
            throw new JWTCreationException(ex.getMessage(), ex.getCause());
        }
    }

    public DecodedJWT verifyToken(String token){
        Algorithm algorithm = Algorithm.HMAC256("a-string-secret-at-least-256-bits-long");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Auth0BackendTransaction")
                .build();

        return verifier.verify(token);
    }

    public String extractSubject(DecodedJWT jwt){
        return jwt.getSubject();
    }

    public boolean isExpired(DecodedJWT token){
        return token.getExpiresAt().before(new Date(System.currentTimeMillis()));
    }
}
