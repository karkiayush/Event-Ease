package com.karkiayush.eventease.config;


import com.karkiayush.eventease.filter.UserProvisioningFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //SecurityConfig being a configuration class
public class SecurityConfig {
    //We need to insert the filter which we created in the filter chain. For that we need to create a bean of type SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            UserProvisioningFilter userProvisioningFilter
    ) throws Exception {
        //Whenever we configure a spring security filter chain, we always need to authorize the requests
        http
                .authorizeHttpRequests(
                        //The lambda authorize does the authentication of all the request. Basically it follows like "catch-all" rule
                        authorize -> authorize.anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(
                        //Since we're replacing SecurityFilterChain bean with our custom filter, we should also specify the oAuth2 resource server
                        oauth2 -> oauth2.jwt(Customizer.withDefaults())
                )
                .addFilterAfter(userProvisioningFilter, BearerTokenAuthenticationFilter.class);

        return http.build();
    }
}
