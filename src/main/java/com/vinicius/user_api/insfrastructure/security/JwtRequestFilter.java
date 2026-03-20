package com.vinicius.user_api.insfrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Pega o header Authorization da requisição
        final String authorizationHeader = request.getHeader("Authorization");

        String path = request.getRequestURI();

        // Ignora endpoints do Swagger (não precisa autenticar)
        if (path.startsWith("/v3/api-docs") ||
                path.startsWith("/swagger-ui") ||
                path.equals("/swagger-ui.html")) {

            chain.doFilter(request, response); // continua sem validar
            return;
        }

        // Verifica se existe token e se começa com "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            final String token = authorizationHeader.substring(7); // remove "Bearer "

            try {
                // Extrai o username do token
                final String username = jwtUtil.extractUsername(token);

                // Se existe username e ainda não está autenticado
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // Busca o usuário no banco
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // Valida o token com o usuário real
                    if (jwtUtil.validateToken(token, userDetails.getUsername())) {

                        // Cria objeto de autenticação
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        // Define o usuário como autenticado no contexto do Spring
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }

            } catch (Exception e) {
                // Se der erro (token inválido, expirado, etc)
                System.out.println("Token inválido: " + e.getMessage());
            }
        }

        // Continua a execução da requisição
        chain.doFilter(request, response);
    }
}