package com.esisalama.marissamayer.controller.form

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UtilisateurRegisterForm(
        @field:NotBlank(message = "Le nom est obligatoire")
        val nom: String,
        @field:NotBlank(message = "Le prenom est obligatoire")
        val prenom: String,
        @field:NotBlank(message = "L'email est obligatoire")
        @field:Email(message = "L'email est invalide")
        val email: String,
        @field:NotBlank(message = "Le mot de passe est obligatoire")
        @field:Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
        val password: String,
) {
    constructor() : this("", "", "", "")
}