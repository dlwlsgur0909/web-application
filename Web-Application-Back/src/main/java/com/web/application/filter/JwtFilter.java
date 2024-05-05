package com.web.application.filter;

import com.web.application.auth.dto.response.TokenInfo;
import com.web.application.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader("Authorization");

        // Token 이 있는지 확인
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("Token is not valid - {}",request.getRequestURL());
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1];

        // Token 만료 확인
        if(!jwtUtil.validateToken(token)) {
            filterChain.doFilter(request, response);

            return;
        }

        // Token 정보 추출
        TokenInfo tokenInfo = jwtUtil.getTokenInfo(token);

        if(tokenInfo == null) {
            log.error("Token is null - {}",request.getRequestURL());
            filterChain.doFilter(request, response);

            return;
        }

//        String role = tokenInfo.getRole();

//        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(tokenInfo, null, List.of(new SimpleGrantedAuthority(role)));
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(tokenInfo, null, List.of());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
