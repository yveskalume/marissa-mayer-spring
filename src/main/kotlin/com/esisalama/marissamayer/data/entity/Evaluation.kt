package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
data class Evaluation(
        @Column(nullable = false)
        val commentaire: String,

        @Column(nullable = false)
        val createdAt: Instant
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToOne
    @JoinColumn(name = "cours_id", nullable = false)
    val cours: Cours? = null

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    val utilisateur: Utilisateur? = null
}