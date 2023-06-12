package com.esisalama.marissamayer.data.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
data class Notification(
        @Column(nullable = false)
        val message: String,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val statuts: NotificationStatus,

        @Column(nullable = false)
        val createdAt: Instant
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    val utilisateur: Utilisateur? = null
}

enum class NotificationStatus {
    LUE, NON_LUE
}
