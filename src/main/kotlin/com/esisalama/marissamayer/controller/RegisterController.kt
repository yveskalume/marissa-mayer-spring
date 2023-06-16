package com.esisalama.marissamayer.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RegisterController {

    @GetMapping("/register")
    fun getRegister() : String {
        return "auth/register"
    }
}