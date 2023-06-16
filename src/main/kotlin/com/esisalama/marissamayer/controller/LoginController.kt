package com.esisalama.marissamayer.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {

    @GetMapping("/login")
    fun showLogin() : String {
        return "auth/login"
    }
}