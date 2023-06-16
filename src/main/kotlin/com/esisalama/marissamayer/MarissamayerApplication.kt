package com.esisalama.marissamayer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.web.SecurityFilterChain

@SpringBootApplication
class MarissamayerApplication

fun main(args: Array<String>) {
	runApplication<MarissamayerApplication>(*args)
}

@Configuration
class SecurityConfiguration {
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
				authorize(anyRequest, permitAll)
			}
			formLogin {
				loginPage = "/login"
				loginProcessingUrl = "/login"
				defaultSuccessUrl("/",false)
				failureUrl = "/login?error"
				permitAll()
			}
			logout {
				logoutUrl = "/logout"
				logoutSuccessUrl = "login?logout"
			}
			httpBasic {
			}
		}
		return http.build()
	}
}
