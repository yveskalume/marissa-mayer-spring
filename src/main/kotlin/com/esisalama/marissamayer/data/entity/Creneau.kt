package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Creneau(
        @Column(nullable = false)
        var date: LocalDateTime? = null,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var statuts: CreneauStatuts,

        @ManyToOne
        @JoinColumn(name = "cours_id", nullable = false)
        var cours: Cours? = null,

        @OneToOne(mappedBy = "creneau", cascade = [CascadeType.ALL])
        var reservation: Reservation? = null,

        @ManyToOne
        @JoinColumn(name = "utilisateur_id", nullable = true)
        var utilisateur: Utilisateur? = null
) {
    constructor() : this(date = null, statuts = CreneauStatuts.LIBRE, cours = null,reservation = null,utilisateur = null)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}

enum class CreneauStatuts {
    LIBRE, OCCUPEE
}