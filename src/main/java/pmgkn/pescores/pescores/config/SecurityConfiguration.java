package pmgkn.pescores.pescores.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import pmgkn.pescores.pescores.service.oauth.OAuthSuccessHandler;
import pmgkn.pescores.pescores.service.PEUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


//    private PEUserDetailsService peUserDetailsService;
//    private final String rememberMeKey;
//
//    public SecurityConfiguration(PEUserDetailsService peUserDetailsService,
//                                 @Value("${pescores.remember.key}") String rememberMeKey) {
//        this.peUserDetailsService = peUserDetailsService;
//        this.rememberMeKey = rememberMeKey;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity/*,OAuthSuccessHandler oAuthSuccessHandler*/) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                // Define which urls are visible by which users
                authorizeRequests -> authorizeRequests
                        // All static resources which are situated in js, images, css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // allow actuator endpoints
//                        .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
//                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // Allow anyone to see the home page, the registration page and the login form
                        .requestMatchers("/", "/contact", "/register", "/login").permitAll()
                        // all other requests are authenticated.
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> {
                    formLogin
                            // redirect here when we access something which is not allowed.
                            // also this is the page where we perform login.
                            .loginPage("/users/login")
                            // The names of the input fields (in our case in auth-login.html)
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(
                logout -> {
                    logout
                            // the URL where we should POST something in order to perform the logout
                            .logoutUrl("/users/logout")
                            // where to go when logged out?
                            .logoutSuccessUrl("/")
                            // invalidate the HTTP session
                            .invalidateHttpSession(true);
                }
        )
//                .rememberMe(
//                rememberMe ->
//                        rememberMe
//                                .key(rememberMeKey)
//                                .rememberMeParameter("rememberme")
//                                .rememberMeCookieName("rememberme"))
//        .oauth2Login(
//                oauth -> oauth.successHandler(oAuthSuccessHandler))
        .build();
    }

}
