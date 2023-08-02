package com.app.reactivities.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends AbstractSecurityWebApplicationInitializer {
    @Bean
    public UserDetailsManager userDetailsService() {
        var user = User.withDefaultPasswordEncoder()
                .username("ruben")
                .password("ruben")
                .authorities("USER")
                .build();

        var admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .authorities("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(Customizer.withDefaults());
//        http.formLogin(Customizer.withDefaults());
        http
                .httpBasic(withDefaults())
                .authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(HttpMethod.POST, "/activities/*").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/activities/*").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/activities/*").hasAuthority("ADMIN")

                        .requestMatchers("/activities", "/activities/*").hasAuthority("USER"));
        return http.build();
    }


//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.userDetailsService(users());
//
//        return http.build();
//    }

}
