package spring.app.config;

import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import java.util.logging.Filter;

public class WebSecurityConfigurerAdapter implements WebSecurityConfigurer<WebSecurity> {
    public void init(WebSecurityConfig builder) throws Exception {

    }

    public void configure(WebSecurityConfig builder) throws Exception {

    }

    @Override
    public void init(WebSecurity builder) throws Exception {

    }

    @Override
    public void configure(WebSecurity builder) throws Exception {

    }
}
