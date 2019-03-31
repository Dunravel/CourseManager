package pl.sda.project.coursemanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;

//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    SignForm signForm;
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/")
//                .permitAll().and()
//                .authorizeRequests()
//                .antMatchers("/console/**")
//                .permitAll();
//
//        httpSecurity.csrf().disable();
//        httpSecurity.headers().frameOptions().disable();
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder.jdbcAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .dataSource(dataSource);
//    }

//    @Bean
//    public UserDetailsService userDetailsService (SignForm signForm) {
//        UserDetails userDetails = User.UserBuilder
//                .username(signForm.getUsername())
//                .password(signForm.getPassword())
//    }
//}
