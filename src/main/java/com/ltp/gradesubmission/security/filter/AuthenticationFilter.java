package com.ltp.gradesubmission.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.security.SecurityConstants;
import com.ltp.gradesubmission.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private CustomAuthenticationManager authenticationManager;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

       try{
           User user= new ObjectMapper().readValue(request.getInputStream(),User.class);
           Authentication authentication=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
           return authenticationManager.authenticate(authentication);
       } catch (IOException e){
           throw new RuntimeException();
       }
//       return super.attemptAuthentication(request,response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token= JWT.
                create().
                withSubject(authResult.getName()).
                withExpiresAt(new Date(System.currentTimeMillis()+ SecurityConstants.TOKEN_EXPIRATION)).
                sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

        response.addHeader(SecurityConstants.AUTHORIZATION,SecurityConstants.BEARER+token);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        System.out.println("Invalid credentials");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Incorrect Credentials");
        response.getWriter().flush();
    }


}
