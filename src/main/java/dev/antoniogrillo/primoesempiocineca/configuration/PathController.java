package dev.antoniogrillo.primoesempiocineca.configuration;

import dev.antoniogrillo.primoesempiocineca.filter.JwtFilter;
import dev.antoniogrillo.primoesempiocineca.model.Ruolo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class PathController {

    private final JwtFilter filter;
    private final AuthenticationProvider provider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .requestMatchers("/user/**").hasAnyRole(Ruolo.USER.name(),Ruolo.ADMIN.name(),Ruolo.SUPERADMIN.name())
                        .requestMatchers("/admin/**").hasRole(Ruolo.ADMIN.name())
                        .requestMatchers("/superadmin/**").hasRole(Ruolo.SUPERADMIN.name())
                        .anyRequest().permitAll()
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(provider);
        return http.build();
    }
}
