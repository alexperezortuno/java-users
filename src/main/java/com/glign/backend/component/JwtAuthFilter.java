package com.glign.backend.component;

import com.glign.backend.jpa.entity.User;
import com.glign.backend.repository.UserRepository;
import com.glign.backend.util.ResponseCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private JwtTokenProvider tokenProvider;
    private UserRepository userRepository;

    @Autowired
    private void setJwtUtil(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // Extraer el token del encabezado y validar el token
                String token = authHeader.replace("Bearer ", "");
                var valid = this.tokenProvider.validateToken(token);
                if (!valid) {
                    // Si el token no es válido, devolver un error 401
                    writeUnauthorizedError(response, HttpServletResponse.SC_UNAUTHORIZED, ResponseCode.INVALID_TOKEN.getMessage());
                    return;
                }

                var uuid = this.tokenProvider.getUserIdFromToken(token);
                User user = userRepository.findByUuid(UUID.fromString(uuid));

                if (user == null) {
                    writeUnauthorizedError(response, HttpServletResponse.SC_NOT_FOUND, ResponseCode.INVALID_TOKEN.getMessage());
                    return;
                }

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            filterChain.doFilter(request, response);
    }

    private void writeUnauthorizedError(HttpServletResponse response,
                                        int status,
                                        String errorMessage) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"".concat(errorMessage).concat("\"}"));
    }
}
