package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant

@Entity
data class Paiement(
        @Column(nullable = false)
        val montant: BigDecimal,

        @Column(nullable = false)
        val createdAt: Instant
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    val reservation: Reservation? = null
}