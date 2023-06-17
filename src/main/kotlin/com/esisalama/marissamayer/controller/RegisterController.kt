package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Utilisateur
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import com.esisalama.marissamayer.data.services.UtilisateurService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
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
    fun postRegister(@ModelAttribute utilisateur: Utilisateur, model: Model) : String {
        utilisateurService.save(utilisateur)
        return "redirect:/login"
    }

}