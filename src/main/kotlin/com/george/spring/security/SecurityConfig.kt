package com.george.spring.security

import com.george.spring.security.ApplicationUserRole.ADMIN
import com.george.spring.security.ApplicationUserRole.STUDENT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder


    override fun configure(http: HttpSecurity?) {
        http?.let {
            it
                // .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrf().disable()

                .authorizeRequests()

                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()

                // ADMIN only can send `POST` request to this pattern
                // any users with another role will not be able to access this route
                .antMatchers(POST,"/api/v1/users").hasRole(ADMIN.name)
                .antMatchers(POST,"/api/v1/users").hasAuthority("")

                .anyRequest().authenticated().and().httpBasic()

        }
    }

    @Bean
    override fun userDetailsServiceBean(): UserDetailsService {
        val george = User.builder()
            .username("george")
            .password(passwordEncoder.encode("0000"))
            .roles(ADMIN.name) // "ROLE_" + "ADMIN" = "ROLE_ADMIN"
            .build()

        val joseph = User.builder()
            .username("joseph")
            .password(passwordEncoder.encode("0000"))
            .roles(STUDENT.name) // "ROLE_" + "STUDENT" = "ROLE_STUDENT"
            .build()

        return InMemoryUserDetailsManager(george, joseph)
    }

}