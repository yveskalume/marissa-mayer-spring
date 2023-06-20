package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant

@Entity
data class Paiement(
        @Column(nullable = false)
        val montant: Double,

        @OneToOne
        @JoinColumn(name = "reservation_id", nullable = false)
        val reservation: Reservation? = null,

        @Column(nullable = false)
        val createdAt: Instant
) {
    constructor() : this(0.0,null, Instant.now())
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}