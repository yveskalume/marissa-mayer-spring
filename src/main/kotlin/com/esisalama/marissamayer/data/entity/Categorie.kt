package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.Instant

@Entity
data class Categorie(

        @field:NotBlank(message = "Le nom est obligatoire")
        @Column(nullable = false)
        val nom: String,

        @Column(nullable = false)
        val createdAt: Instant
) {
    constructor() : this("", Instant.now())
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToMany(mappedBy = "categorie")
    val cours: List<Cours> = emptyList()
}