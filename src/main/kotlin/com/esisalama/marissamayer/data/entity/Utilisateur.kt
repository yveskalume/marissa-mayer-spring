package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.Instant

@Entity
data class Utilisateur(
        @field:NotBlank(message = "Le nom est obligatoire")
        @Column(nullable = false)
        val nom: String,

        @field:NotBlank(message = "Le prenom est obligatoire")
        @Column(nullable = false)
        val prenom: String,

        @field:NotBlank(message = "L'email est obligatoire")
        @field:Email(message = "L'email est invalide")
        @Column(nullable = false, unique = true)
        val email: String,

        @field:NotBlank(message = "Le mot de passe est obligatoire")
        @field:Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
        @Column(nullable = false)
        val password: String,

        @Column(nullable = false)
        val createdAt: Instant,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val role: Role,

        @Column(nullable = true)
        val localisation: String? = null,
) {
    constructor() : this("", "", "", "", Instant.now(), Role.ETUDIANT)
    constructor(nom: String, prenom: String, email: String, password: String) : this(nom, prenom, email, password, Instant.now(), Role.ETUDIANT)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

enum class Role {
    ETUDIANT, PRESTATAIRE
}