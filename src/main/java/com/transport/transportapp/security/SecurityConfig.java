package com.transport.transportapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Hier kannst du die Zugriffsbeschränkungen für deine REST-Endpunkte festlegen
        http.authorizeRequests()
                .antMatchers("/users/login").permitAll() // Login-Endpunkt soll ohne Authentifizierung zugänglich sein
                .antMatchers("/users/**").hasRole("USER") // Andere Endpunkte sollen nur für Benutzer mit der Rolle
                                                          // "USER" zugänglich sein
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider)); // JWT-Token-Konfiguration hinzufügen
    }

    @Override
    public void configure(WebSecurity web) {
        // Hier kannst du ignorierte Pfade für die Sicherheitskonfiguration festlegen
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}