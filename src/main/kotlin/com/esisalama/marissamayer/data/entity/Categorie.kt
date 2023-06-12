package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
data class Categorie(
        @Column(nullable = false)
        val nom: String,

        @Column(nullable = false)
        val createdAt: Instant
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}