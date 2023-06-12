package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository : JpaRepository<Reservation, Long>