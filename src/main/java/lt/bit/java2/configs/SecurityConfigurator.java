package lt.bit.java2.configs;

import lt.bit.java2.entities.Account;
import lt.bit.java2.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(
        jsr250Enabled = true,
        securedEnabled = true,
        prePostEnabled = true)
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    private final AccountRepository accountRepository;

    public SecurityConfigurator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/mvc/driver/list").permitAll()
////                .antMatchers("/mvc/driver/edit-form").hasRole("USER")
////                .antMatchers("/mvc/driver/delete").hasRole("ADMIN")
//                .antMatchers("/contacts").permitAll()
//                .anyRequest().authenticated()
//
//            .and()
//            .formLogin()
//
//            .and()
//            .logout()
//            .logoutSuccessUrl("/mvc/driver/list");
//    }


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
                    //return Arrays.asList(() -> "ROLE_" + account.getRole());
                    List<GrantedAuthority> authorities = new ArrayList<>();

                    if (account.getRoles() != null) {
                        account.getRoles().forEach(role -> {
                            // authorities.add(() -> "ROLE_" + role.getName());
                            if (role.getPrivileges() != null) {
                                role.getPrivileges().forEach(p -> {
                                    authorities.add(() -> "ROLE_" + p.getName());
                                });
                            }
                        });
                    }

                    return authorities;
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

@EnableWebSecurity
class SecureConfig {

    @Configuration
    @Order(1)
    static class ApiConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**")
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(2)
    static class MvcConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/mvc/driver/list").permitAll()
                    .anyRequest().authenticated()

                    .and()
                    .formLogin()

                    .and()
                    .logout()
                    .logoutSuccessUrl("/mvc/driver/list");
        }
    }
}