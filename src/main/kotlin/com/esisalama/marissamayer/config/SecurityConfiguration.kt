package com.esisalama.marissamayer.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {

    @Autowired
    private lateinit var customUserService: CustomUserService

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

//    @Bean
//    fun authenticationProvider() : AuthenticationProvider {
//        val auth = DaoAuthenticationProvider()
//        auth.setUserDetailsService(customUserService)
//        auth.setPasswordEncoder(passwordEncoder())
//        return auth
//    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf {
                disable()
            }
            headers {
                frameOptions {
                    disable()
                }
            }
            authorizeRequests {
                authorize("/css/**", permitAll)
                authorize("/js/**", permitAll)
                authorize("/images/**", permitAll)
                authorize("/webjars/**", permitAll)
                authorize("/favicon.ico", permitAll)
                authorize("/register", permitAll)
                authorize("/login", permitAll)
                authorize(PathRequest.toH2Console(),permitAll)
                authorize(anyRequest, authenticated)
            }
            formLogin {
                loginPage = "/login"
                loginProcessingUrl = "/login"
                defaultSuccessUrl("/", true)
                failureUrl = "/login?error"
                permitAll()
            }
            logout {
                logoutUrl = "/logout"
                logoutSuccessUrl = "/login?logout"
            }
        }
        return http.build()
    }
}