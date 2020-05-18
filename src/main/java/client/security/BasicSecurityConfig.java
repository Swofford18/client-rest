package client.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("adminX").password("{noop}adminX").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/allUsers").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/getOne").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/editUser").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/addUser").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/deleteUser").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}