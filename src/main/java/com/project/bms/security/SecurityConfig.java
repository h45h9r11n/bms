//package com.project.bms.security;
//
//import com.project.bms.service.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//@Configuration
//@AllArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    private final UserService userService;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userService;
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService());
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public AuthenticationFailureHandler authenticationFailureHandler() {
////        return (request, response, exception) -> {
////            String errorMessage = "Invalid username or password";
////            if (exception instanceof BadCredentialsException) {
////                errorMessage = "Invalid username or password";
////            } else if (exception instanceof DisabledException) {
////                errorMessage = "Your account is disabled";
////            }
////            request.getSession().setAttribute("error", errorMessage);
////            response.sendRedirect("/login?error=true");
////        };
////    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return (request, response, authentication) -> {
//            String role = authentication.getAuthorities().stream()
//                    .map(grantedAuthority -> grantedAuthority.getAuthority())
//                    .findFirst()
//                    .orElse("ROLE_USER");
//
//            if (role.equals("ROLE_ADMIN")) {
//                response.sendRedirect("/admin");
//            } else {
//                response.sendRedirect("/index");
//            }
//        };
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(httpForm -> {
//            httpForm.loginPage("/req/login").permitAll();
//            httpForm.defaultSuccessUrl("/books", true);
//            httpForm.successHandler(authenticationSuccessHandler());
////            httpForm.failureHandler(authenticationFailureHandler());
//        })
//        .authorizeHttpRequests(registry ->{
//
//            registry.requestMatchers("/req/signup","/admin", "/css/**", "/js/**", "/public/**").permitAll();
////            registry.requestMatchers("/admin/**").hasRole("ADMIN");
//            registry.anyRequest().authenticated();
//        }).build();
//
//    }
//
//
//}
