package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.util.*

@Entity
data class Cours(
        @Column(nullable = false)
        val nom: String,


        @Column(nullable = false)
        val prix: Double,

        @Column(nullable = false)
        val description: String,

        val duree: Int? = null,

        val prerequis: String? = null,

        @Column(nullable = false)
        val createdAt: Date,

        @ManyToOne
        @JoinColumn(name = "categorie_id", referencedColumnName = "id", nullable = true)
        val categorie: Categorie? = null,

        @OneToMany(mappedBy = "cours")
        val evaluations: List<Evaluation>? = null,

        @OneToMany(mappedBy = "cours")
        val creneaux: List<Creneau>? = null,

        @ManyToOne
        @JoinColumn(name = "utilisateur_id", referencedColumnName = "id", nullable = false)
        val instructeur: Utilisateur? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}