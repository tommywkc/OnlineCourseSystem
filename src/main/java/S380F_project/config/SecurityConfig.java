package S380F_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/list").hasRole("TEACHER")
                        .requestMatchers("/user/edit/**").hasAnyRole("STUDENT", "TEACHER")
                        .requestMatchers("/lecture/delete/**").hasRole("TEACHER")
                        .requestMatchers("/lecture/**").hasAnyRole("STUDENT", "TEACHER")
                        .requestMatchers("/lecture/createNote/**").hasRole("TEACHER")
                        .requestMatchers("/commentHistory/**").hasAnyRole("STUDENT", "TEACHER")
                        .requestMatchers("/pollHistory/**").hasAnyRole("STUDENT", "TEACHER")
                        .requestMatchers("/poll/**").hasAnyRole("STUDENT", "TEACHER")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .permitAll()
                        .defaultSuccessUrl("/index", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/index")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
