package com.github.barney.messengerapi.filters

import com.github.barney.messengerapi.service.TokenAuthenticationService
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JWTAuthenticationFilter: GenericFilterBean() {

    /**
     * The doFilter() function of the JWTAuthenticationFilter is called by the container
    each time a request/response pair is passed through the filter chain as a result of a client
    request for a resource.
     */
    override fun doFilter(req: ServletRequest, res: ServletResponse, filterChain: FilterChain) {
        val authentication = TokenAuthenticationService.getAuthentication(req as HttpServletRequest)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(req, res)
    }
}