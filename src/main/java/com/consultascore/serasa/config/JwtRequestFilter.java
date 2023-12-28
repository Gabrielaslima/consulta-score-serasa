package com.consultascore.serasa.config;

import com.consultascore.serasa.entity.RegularUser;
import com.consultascore.serasa.service.LoginService;
import com.consultascore.serasa.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    LoginService loginService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * Metodo responsavel por verificar se o JWT é valido e coloca o User no contexto*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = parseJwt(request);

        if (jwt != null && jwtTokenUtil.validateJwtToken(jwt)) {
            String id = jwtTokenUtil.getSubjectFromJwtToken(jwt);

            RegularUser user = loginService.authenticateUserFromId(id);
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(userAuth);
            String updatedToken = jwtTokenUtil.generateToken(Long.valueOf(id));
            response.setHeader("token", updatedToken);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Metodo responsavel por recuperar o JWT do Header*/
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if(StringUtils.isNotBlank(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
