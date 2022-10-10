package com.gowri.ApiGateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Optional;

@Component
public class JwtTokenUtil {

    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public JwtTokenUtil(KeyUtil keyUtil) throws Exception {
        this.privateKey = keyUtil.readPrivateKey();
        this.publicKey = keyUtil.readPublicKey();
    }

    public Optional<String> generateToken() {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
            return Optional.of(token);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return Optional.empty();
    }

    public void validateToken(String token) throws TokenExpiredException {
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        verifier.verify(token);
    }
}
