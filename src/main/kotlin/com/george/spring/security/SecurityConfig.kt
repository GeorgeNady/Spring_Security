package com.george.spring.security

import com.george.spring.security.ApplicationUserRole.ADMIN
import com.george.spring.security.ApplicationUserRole.STUDENT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder


    override fun configure(http: HttpSecurity?) {
        http?.let {
            it
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("index", "/css/*", "/js/*").permitAll()

                // ADMIN only can send `POST` request to this pattern
                // any users with another role will not be able to access this route
                .antMatchers(POST,"/api/v1/courses").hasRole(ADMIN.name)

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

        val dodos = User.builder()
            .username("andrew")
            .password(passwordEncoder.encode("0000"))
            .roles(STUDENT.name) // "ROLE_" + "STUDENT" = "ROLE_STUDENT"
            .build()

        return InMemoryUserDetailsManager(george, dodos)
    }

}