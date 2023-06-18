package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
data class Utilisateur(
        @Column(nullable = false)
        val nom: String,

        @Column(nullable = false)
        val prenom: String,

        @Column(nullable = false)
        val email: String,

        @Column(nullable = false)
        val password: String,

        @Column(nullable = false)
        val createdAt: Instant,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val role: Role
) {
    constructor() : this("", "", "", "", Instant.now(), Role.ETUDIANT)
    constructor(nom: String, prenom: String, email: String, password: String) : this(nom, prenom, email, password, Instant.now(), Role.ETUDIANT)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

enum class Role {
    PRESTATAIRE, ETUDIANT
}