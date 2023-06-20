package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.ReservationStatuts
import com.esisalama.marissamayer.data.repository.CoursRepository
import com.esisalama.marissamayer.data.repository.ReservationRepository
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class InstructorController(
        @Autowired private val coursRepository: CoursRepository,
        @Autowired private val reservationRepository: ReservationRepository,
        @Autowired private val utilisateurRepository: UtilisateurRepository
) {

    @GetMapping(value = ["/instructor/my-courses"], name = "instructor.my-courses")
    fun myCourses(auth: Authentication, model: Model): String {
        val user = auth.principal as User
        val courses = user.username?.let { coursRepository.findByInstructeurEmail(it) } ?: listOf()
        model.addAttribute("courses", courses)

        return "instructor/my_courses"
    }

    @GetMapping(value = ["/instructor/my-reservation"])
    fun myReservations(auth: Authentication, model: Model): String {
        val user = auth.principal as User
        val reservations = reservationRepository.findAll().filter { it.creneau?.cours?.instructeur?.email == user.username }
        model.addAttribute("reservations", reservations)

        return "instructor/my_reservation"
    }

    @GetMapping(value = ["/instructor/my-reservation/reject"])
    fun rejectReservation(@RequestParam("id") id: Long, model: Model): String {
        val reservation = reservationRepository.findById(id)

        if (reservation.isPresent) {
            val mReservation = reservation.get().copy(statuts = ReservationStatuts.REJETEE)
            reservationRepository.save(mReservation)
        }

        return "redirect:/instructor/my-reservation"
    }

    @GetMapping(value = ["/instructor/my-reservation/accept"])
    fun acceptReservation(@RequestParam("id") id: Long, model: Model): String {
        val reservation = reservationRepository.findById(id)

        if (reservation.isPresent) {
            val mReservation = reservation.get().copy(statuts = ReservationStatuts.CONFIRMEE)
            reservationRepository.save(mReservation)
        }

        return "redirect:/instructor/my-reservation"
    }
}