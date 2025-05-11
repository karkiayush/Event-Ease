package com.karkiayush.eventease.filter;


import com.karkiayush.eventease.domain.entity.User;
import com.karkiayush.eventease.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        /*We are going to insert this filter after the spring security authentication has taken place.
         *
         * So we need to have access to that authentication object.
         * */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // if the authentication object is not null, & if the user is authenticated, and if the principal is an instance of JWT, we will extract the keycloak user ID
        if (
                authentication != null
                        && authentication.isAuthenticated()
                        && authentication.getPrincipal() instanceof Jwt jwt
        ) {
            // getSubject() gives the user's id
            UUID keycloakUserId = UUID.fromString(jwt.getSubject());

            // Checking whether our user exists or not. For that we will be using user repository. If the user doesn't exist, create a new user
            if (!userRepository.existsById(keycloakUserId)) {
                User user = new User();
                String userName = jwt.getClaimAsString("preferred_username");
                String userEmail = jwt.getClaimAsString("email");

                user.setId(keycloakUserId);
                user.setName(userName);
                user.setEmail(userEmail);
                userRepository.save(user);
            }
        }
    }
}
