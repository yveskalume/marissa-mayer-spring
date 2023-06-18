package com.esisalama.marissamayer.config

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ThymeleafDialect {

    @Bean
    fun layoutDialect(): LayoutDialect {
        return LayoutDialect()
    }
}