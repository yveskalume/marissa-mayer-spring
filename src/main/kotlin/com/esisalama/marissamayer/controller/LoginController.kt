package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @Autowired
    private lateinit var utilisateurRepository: UtilisateurRepository

    @GetMapping("/login")
    fun showLogin() : String {
        return "auth/login"
    }
}