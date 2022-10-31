package springboot.angular.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import springboot.angular.Constants;

import java.util.Date;

@Component
public class JwtConfig {


    public JwtConfig() {}


    public String generateToken(int id, String username){

        return Jwts.builder()
                .claim("id", id)
                .claim("username", username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, Constants.API_SECRET_KEY)
                .compact();

    }

}
