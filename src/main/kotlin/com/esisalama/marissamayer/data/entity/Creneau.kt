package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Creneau(
        @Column(nullable = false)
        val date: LocalDateTime? = null,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val statuts: CreneauStatuts,

        @ManyToOne
        @JoinColumn(name = "cours_id", nullable = false)
        val cours: Cours? = null,

        @OneToOne(mappedBy = "creneau", cascade = [CascadeType.ALL])
        val reservation: Reservation? = null,

        @ManyToOne
        @JoinColumn(name = "utilisateur_id", nullable = true)
        val utilisateur: Utilisateur? = null
) {
    constructor() : this(date = null, statuts = CreneauStatuts.LIBRE, cours = null,reservation = null,utilisateur = null)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}

enum class CreneauStatuts {
    LIBRE, OCCUPEE
}