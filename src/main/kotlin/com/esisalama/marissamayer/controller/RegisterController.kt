package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Role
import com.esisalama.marissamayer.data.entity.Utilisateur
import com.esisalama.marissamayer.data.services.UtilisateurService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegisterController {

    @Autowired
    private lateinit var utilisateurService: UtilisateurService

    val roles = Role.values()

    @GetMapping("/register")
    fun getRegister(model: Model): String {
        val utilisateur = Utilisateur()
        model.addAttribute("utilisateur", utilisateur)
        model.addAttribute("roles", roles)
        return "auth/register"
    }

    @PostMapping("/register")
    fun postRegister(@Valid utilisateur: Utilisateur, bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.allErrors)
            model.addAttribute("roles", roles)
            return "auth/register"
        }
        println(utilisateur)
        utilisateurService.save(utilisateur)
        return "redirect:/login"
    }

}