package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Reservation
import com.esisalama.marissamayer.data.repository.ReservationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("reservations")
class ReservationController(
        @Autowired private val reservationRepository: ReservationRepository
) {
    @GetMapping("/{id}")
    fun getReservationById(@PathVariable id: Long,model: Model): Any? {
        return reservationRepository.findById(id)
                .orElseThrow { ChangeSetPersister.NotFoundException() }
        return "reservation"
    }

    @PostMapping
    fun createReservation(@RequestBody reservation: Reservation): Reservation {
        return reservationRepository.save(reservation)
    }

    // Implement other CRUD operations for Reservation


}
