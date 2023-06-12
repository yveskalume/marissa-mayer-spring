package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
data class Reservation(
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val statuts: ReservationStatuts,

        @Column(nullable = false)
        val date: Instant,

        @Column(nullable = false)
        val createdAt: Instant
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToOne(mappedBy = "reservation", cascade = [CascadeType.ALL])
    val paiement: Paiement? = null

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    val utilisateur: Utilisateur? = null
}

enum class ReservationStatuts {
    REJETEE, EN_ATTENTE, CONFIRMEE
}