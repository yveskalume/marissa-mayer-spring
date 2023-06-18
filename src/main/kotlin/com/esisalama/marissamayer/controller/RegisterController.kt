package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.controller.form.UtilisateurRegisterForm
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
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class RegisterController {

    @Autowired
    private lateinit var utilisateurService: UtilisateurService

    @GetMapping("/register")
    fun getRegister(model: Model): String {
        val utilisateurForm = UtilisateurRegisterForm()
        model.addAttribute("utilisateurForm", utilisateurForm)
        return "auth/register"
    }

    @PostMapping("/register")
    fun postRegister(@ModelAttribute utilisateurForm: UtilisateurRegisterForm, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "auth/register"
        }

        val utilisateur = Utilisateur(
                nom = utilisateurForm.nom,
                prenom = utilisateurForm.prenom,
                email = utilisateurForm.email,
                password = utilisateurForm.password
        )
        utilisateurService.save(utilisateur)
        return "redirect:/login"
    }

}