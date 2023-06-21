package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
data class Reservation(
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var statuts: ReservationStatuts,

        @Column(nullable = false)
        val createdAt: Instant,

        @OneToOne
        @JoinColumn(name = "creneau_id", nullable = false)
        val creneau: Creneau? = null,

        @OneToOne(mappedBy = "reservation", cascade = [CascadeType.ALL])
        val paiement: Paiement? = null,

        @ManyToOne
        @JoinColumn(name = "utilisateur_id", nullable = false)
        val utilisateur: Utilisateur? = null
) {

    constructor() : this(statuts = ReservationStatuts.EN_ATTENTE, createdAt = Instant.now(), creneau = null, paiement = null, utilisateur = null)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}

enum class ReservationStatuts {
    REJETEE, EN_ATTENTE, CONFIRMEE
}

fun Reservation.isRejected(): Boolean {
    return statuts == ReservationStatuts.REJETEE
}

fun Reservation.isConfirmed(): Boolean {
    return statuts == ReservationStatuts.CONFIRMEE
}

fun Reservation.isPending(): Boolean {
    return statuts == ReservationStatuts.EN_ATTENTE
}