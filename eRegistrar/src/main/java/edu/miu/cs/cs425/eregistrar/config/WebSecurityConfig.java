package edu.miu.cs.cs425.eregistrar.config;

//import edu.mum.cs.cs425.demos.elibrary_springsec_1.service.ElibraryUserDetailsService;
import edu.miu.cs.cs425.eregistrar.model.RoleType;
import edu.miu.cs.cs425.eregistrar.service.serviceImpl.EregistrationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(EregistrationUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return auth.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests.requestMatchers("/api/v1/admins/**")
                                                              .hasRole(RoleType.ADMIN.name())
                                                              .requestMatchers("/api/v1/students/**")
                                                              .hasRole(RoleType.STUDENT.name())
                                                              .requestMatchers("/api/v1/registrars/**")
                                                              .hasRole(RoleType.REGISTRAR.name())

                                                              .anyRequest()
                                                              .permitAll()
                                  ).securityMatcher("/api/v1/auth/**");

        return http.build();
    }


}

