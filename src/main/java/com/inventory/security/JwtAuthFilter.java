package com.inventory.security;

import com.inventory.exception.GenericException;
import com.inventory.repository.SellerRepo;
import com.inventory.security.userdetails.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {


    private JwtService jwtService;
    private SellerRepo sellerRepo;

    @Autowired
    public JwtAuthFilter(
            JwtService jwtService,
            SellerRepo sellerRepo,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.sellerRepo = sellerRepo;
    }

    @Autowired
    private CustomUserDetails customUserDetails;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String userEmail = null;
        String token = null;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            if (!jwtService.isTokenExpired(token) && authentication == null) {
                userEmail = jwtService.extractUsername(token);
                CustomUserDetails userDetails = this.modelMapper.map(this.sellerRepo.findByEmail(userEmail), CustomUserDetails.class);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        customUserDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception exception) {
            throw new GenericException("Token Not Valid");

        }

        filterChain.doFilter(request, response);
    }
//        catch (Exception exception) {
//            handlerExceptionResolver.resolveException(request, response, null, exception);
}
