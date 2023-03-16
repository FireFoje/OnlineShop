package spring.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spring.app.service.UserService;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService service;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity security) throws Exception {
        security.cors().and().csrf().disable().authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/registration").fullyAuthenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/news").hasRole("USER")
                        .anyRequest().authenticated())
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/user").failureUrl("/login?error");
    }

    public Filter build() throws Exception {
        return null;
    }

    @Override
    public void init(WebSecurity builder) throws Exception {

    }

    @Override
    public void configure(WebSecurity builder) throws Exception {

    }
}
