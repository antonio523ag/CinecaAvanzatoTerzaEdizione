package dev.antoniogrillo.primoesempiocineca.configuration;

import dev.antoniogrillo.primoesempiocineca.repository.jparepo.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UtenteRepository repo;

    @Bean
    protected UserDetailsService getService(){
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return repo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Utente non trovato"));
//            }
//        };
        return u->repo.findByEmail(u).orElseThrow(()->new UsernameNotFoundException("Utente non trovato"));
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager getManager(AuthenticationConfiguration context){
        return context.getAuthenticationManager();
    }

    @Bean
    protected AuthenticationProvider getProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(getService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
