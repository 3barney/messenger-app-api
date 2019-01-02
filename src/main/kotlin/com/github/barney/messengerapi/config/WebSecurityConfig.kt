package com.github.barney.messengerapi.config

import com.github.barney.messengerapi.filters.JWTAuthenticationFilter
import com.github.barney.messengerapi.filters.JWTLoginFilter
import com.github.barney.messengerapi.service.AppUserDetailsService
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig(val userDetailsService: AppUserDetailsService): WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {    // Configure URL paths
        super.configure(http)
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/registrations") // Permit all request to route not to check for auth
                .permitAll()
                .antMatchers(HttpMethod.POST, "/login") // Also permit without checking for security
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()  // below Filter's only the /login route
                .addFilterBefore(JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java) // Filter outher req
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {    // Set up userDetailsService Specifies password encooder to be used
        super.configure(auth)
        auth.userDetailsService<UserDetailsService>(userDetailsService).passwordEncoder(BCryptPasswordEncoder())
    }
}












