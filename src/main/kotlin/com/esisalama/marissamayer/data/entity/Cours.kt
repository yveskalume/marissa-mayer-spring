package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
data class Cours(
        @Column(nullable = false)
        val nom: String,

        @Column(nullable = false)
        val description: String,

        val duree: Int? = null,

        val prerequis: String? = null,

        @Column(nullable = false)
        val createdAt: Instant
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    val categorie: Categorie? = null

    @OneToMany(mappedBy = "cours")
    val evaluations: List<Evaluation>? = null

    @OneToMany(mappedBy = "cours")
    val creneaux: List<Creneau>? = null
}