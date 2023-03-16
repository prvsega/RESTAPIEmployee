package by.prvsega.restservice.config;

import by.prvsega.restservice.security.EmployeeDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final EmployeeDetailsServiceImpl employeeDetailsService;

    @Autowired
    public SecurityConfig(EmployeeDetailsServiceImpl employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
            http.authorizeRequests()
                .antMatchers("/auth/admin").hasRole("ADMIN")
                    .antMatchers("/auth/am").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/auth/manager").hasRole("MANAGER")
                    .antMatchers("/auth/user").hasRole("USER")
                .antMatchers("/auth/login", "/error", "/logout").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/auth/test", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    protected PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder();
      return NoOpPasswordEncoder.getInstance();
    }
}
