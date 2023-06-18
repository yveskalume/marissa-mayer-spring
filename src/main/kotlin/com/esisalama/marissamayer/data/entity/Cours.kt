package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.Instant

@Entity
data class Cours(

        @field:NotBlank(message = "Le nom du cours est obligatoire")
        @Column(nullable = false)
        val nom: String,

        @field:DecimalMin(value = "1.0", message = "Le prix du cours doit être supérieur à 0")
        @Column(nullable = false)
        val prix: Double,

        @field:NotBlank(message = "La description du cours est obligatoire")
        @Column(nullable = false)
        val description: String,

        @field:NotNull(message = "La durée du cours est obligatoire")
        val duree: Int? = null,

        val prerequis: String? = null,

        @Column(nullable = false)
        val createdAt: Instant,

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
    constructor() : this("", 0.0, "", 0, null, Instant.now(), null, null, null)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}