package com.ltp.gradesubmission.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterTwo implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try{
            User user=new ObjectMapper().readValue(((HttpServletRequest) servletRequest).getInputStream(), User.class);
            System.out.println("User from filter two "+user.getUsername());
            System.out.println("User from filter two "+user.getPassword());
        }catch (IOException e){
            throw new RuntimeException();
        }

        System.out.println("No exception occured");
        filterChain.doFilter(servletRequest,servletResponse);


    }
}
