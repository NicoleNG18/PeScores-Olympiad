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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
//    private final String rememberMeKey;

//    @Autowired
//    public SecurityConfiguration(@Value("${pescores.rememberMeKey}")
//                                 String rememberMeKey) {
//        this.rememberMeKey = rememberMeKey;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
//                .csrf()
//                .disable()
                .authorizeHttpRequests(
                        // Define which urls are visible by which users
                        authorizeRequests -> authorizeRequests
                                // All static resources which are situated in js, images, css are available for anyone
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                // Allow anyone to see the home page, the registration page and the login form
                                .requestMatchers("/users/login"
                                        , "/users/register",
                                        "/users/login-error",
//                                        "/api/notes/{id}",
                                        "/contact").permitAll()
                                .requestMatchers("/notes",
                                        "/classes").authenticated()
                                .requestMatchers("/").anonymous()
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
                                    .defaultSuccessUrl("/classes")
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
//                .rememberMe(rememberMe ->
//                        rememberMe
//                                .key(rememberMeKey)
//                                .rememberMeParameter("rememberme")
//                                .rememberMeCookieName("rememberme")
//                                .tokenValiditySeconds(5 * 24 * 60 * 60))
                .build();

    }

}