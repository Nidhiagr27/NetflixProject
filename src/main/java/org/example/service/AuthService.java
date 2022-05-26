package org.example.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.accessor.AuthAccessor;
import org.example.accessor.UserAccessor;
import org.example.accessor.models.UserDTO;
import org.example.exceptions.InvalidCredentialsException;
import org.example.security.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthService {

    @Autowired
    private UserAccessor userAccessor;

    @Autowired
    private AuthAccessor authAccessor;

    /**
     *
     * @param email: Email of the user who wants to login
     * @param password: Password of the user who wants to login
     * @return : Jwt Token if email and password combination is correct
     */
    public String login(final String email, final String password) {
        UserDTO userDTO = userAccessor.getUserByEmail(email);
        if (userDTO != null && userDTO.getPassword().equals(password)) {
            String token = Jwts.builder()
                    .setSubject(email)
                    .setAudience(userDTO.getRole().name())
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY.getBytes())
                    .compact();
            authAccessor.storeToken(userDTO.getUserId(), token);
            return token;
        }
        throw new InvalidCredentialsException("Either the email or password is incorrect!");
    }

    public void logout(final String token){
        authAccessor.deleteAuthByToken(token);
    }
}
