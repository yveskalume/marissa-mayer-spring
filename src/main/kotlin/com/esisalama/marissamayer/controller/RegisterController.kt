package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Utilisateur
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import com.esisalama.marissamayer.data.services.UtilisateurService
import jakarta.validation.Valid
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegisterController {

    @Autowired
    private lateinit var utilisateurService: UtilisateurService

    @GetMapping("/register")
    fun getRegister(model: Model) : String {
        val utilisateur = Utilisateur()
        model.addAttribute("utilisateur", utilisateur)
        return "auth/register"
    }

    @PostMapping("/register")
    fun postRegister(@Valid utilisateur: Utilisateur,bindingResult: BindingResult,model: Model) : String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.allErrors)
            return "auth/register"
        }
        utilisateurService.save(utilisateur)
        return "redirect:/login"
    }

}