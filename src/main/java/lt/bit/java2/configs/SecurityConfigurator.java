package lt.bit.java2.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/AntPathMatcher.html:
                // ? matches one character
                // * matches zero or more characters
                // ** matches zero or more directories in a path
                // {spring:[a-z]+} matches the regexp [a-z]+ as a path variable named "spring"
                .antMatchers("/api/**").permitAll()
                .antMatchers("/mvc/driver/list").permitAll()
                .antMatchers("/contacts").permitAll()
                .anyRequest().authenticated()

            .and()
            .formLogin()

            .and()
            .logout()
            .logoutSuccessUrl("/mvc/driver/list");
    }
}
