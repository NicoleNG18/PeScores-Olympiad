package pmgkn.pescores.pescores.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        // Define which urls are visible by which users
                        authorizeRequests -> authorizeRequests
                                // All static resources which are situated in js, images, css are available for anyone
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                // Allow anyone to see the home page, the registration page and the login form
                                .requestMatchers("/users/login"
                                        , "/users/register",
                                        "/users/login-error").permitAll()

                                .requestMatchers("/tasks","/classes").authenticated()

                                .requestMatchers("/", "/contact").anonymous()

                                .requestMatchers("/schools/**","/users/roles/**").hasRole(UserRoleEnum.SUPERADMIN.name())

                                .requestMatchers("/classes/add","/classes/edit/**",
                                "/classes/edited/**","/classes/delete/**","/students/**","/classes/{idTeacher}/{id}","/classes").hasRole(UserRoleEnum.ADMIN.name())

                                .requestMatchers("/students/{id}").hasAnyRole(UserRoleEnum.USER.name(),UserRoleEnum.ADMIN.name())

                                .requestMatchers("/denseBall", "/jump",
                                        "/thirty", "/twoHundred",
                                        "/tTest","/classes/{id}","/classes").hasRole(UserRoleEnum.USER.name())

                                // all other requests are authenticated.
                                .anyRequest().authenticated()
                ).formLogin(
                        formLogin -> {
                            formLogin
                                    // redirect here when we access something which is not allowed.
                                    // also this is the page where we perform login.
                                    .loginPage("/users/login")
                                    // The names of the input fields (in our case in login.html)
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/tasks")
                                    .failureForwardUrl("/users/login-error");
                        }
                ).logout(
                        logout -> {
                            try {
                                logout
                                        // the URL where we should POST something in order to perform the logout
                                        .logoutUrl("/users/logout")
                                        // where to go when logged out?
                                        .logoutSuccessUrl("/")
                                        // invalidate the HTTP session
                                        .invalidateHttpSession(true)
                                        .deleteCookies("JSSESIONID","lang")
                                        .and().
                                        securityContext().
                                        securityContextRepository(securityContextRepository());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .build();

    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

}