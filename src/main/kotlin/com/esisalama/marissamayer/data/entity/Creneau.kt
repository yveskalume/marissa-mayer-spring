package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*

@Entity
data class Creneau(
        @Enumerated(EnumType.STRING)
        val jour: Jour,

        @Column(nullable = false)
        val heureDebut: String,

        @Column(nullable = false)
        val heureFin: String,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val statuts: CreneauStatuts
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

enum class Jour {
    LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE
}

enum class CreneauStatuts {
    LIBRE, OCCUPEE
}