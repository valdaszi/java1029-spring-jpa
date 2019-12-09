package lt.bit.java2.configs;

import lt.bit.java2.entities.Account;
import lt.bit.java2.repositories.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    private final AccountRepository accountRepository;

    public SecurityConfigurator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

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
//                .antMatchers("/mvc/driver/edit-form").hasRole("USER")
//                .antMatchers("/mvc/driver/delete").hasRole("ADMIN")
                .antMatchers("/contacts").permitAll()
                .anyRequest().authenticated()

            .and()
            .formLogin()

            .and()
            .logout()
            .logoutSuccessUrl("/mvc/driver/list");
    }


    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
//        List<UserDetails> users = Arrays.asList(
//                User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build(),
//                User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build()
//        );
//        return new InMemoryUserDetailsManager(users);
        return username -> {
            Account account = accountRepository.findByEmail(username);
            if (account == null) {
                throw new UsernameNotFoundException(username + " not found");
            }
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return Arrays.asList(() -> "ROLE_" + account.getRole());
                }

                @Override
                public String getPassword() {
                    return account.getPassword();
                }

                @Override
                public String getUsername() {
                    return account.getEmail();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return account.getDisabled() == null || !account.getDisabled();
                }
            };
        };
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
