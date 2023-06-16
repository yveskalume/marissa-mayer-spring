package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.repository.CoursRepository
import com.github.javafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
        @Autowired private val userDetailsService: UserDetailsService,
        @Autowired private val coursRepository: CoursRepository,
) {

    @GetMapping("/")
    fun index(): String {
        val cours = coursRepository.findAll()
        println(cours)
        return "home"
    }
}