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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}

enum class Role {
    PRESTATAIRE, ETUDIANT
}