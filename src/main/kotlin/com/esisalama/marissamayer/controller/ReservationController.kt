package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Creneau
import com.esisalama.marissamayer.data.entity.CreneauStatuts
import com.esisalama.marissamayer.data.entity.Paiement
import com.esisalama.marissamayer.data.entity.Reservation
import com.esisalama.marissamayer.data.repository.CreneauRepository
import com.esisalama.marissamayer.data.repository.PaiementRepository
import com.esisalama.marissamayer.data.repository.ReservationRepository
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.time.LocalDateTime

@Controller
class ReservationController(
        @Autowired private val reservationRepository: ReservationRepository,
        @Autowired private val creneauRepository: CreneauRepository,
        @Autowired private val utilisateurRepository: UtilisateurRepository,
        @Autowired private val paiementRepository: PaiementRepository,
) {

    @GetMapping("reservations")
    fun getAllMyReservations(auth: Authentication, model: Model): String {
        val user = auth.principal as User
        val reservations = reservationRepository.findAll().filter { it.utilisateur?.email == user.username }

        model.addAttribute("reservations", reservations)

        return "reservation/index"
    }
    @GetMapping("cours/{coursId}/reserver")
    fun showReservationForm(@PathVariable coursId: Long, auth: Authentication, model: Model): String {
        val creneaux = creneauRepository.findAllByCoursId(coursId).filter { it.statuts == CreneauStatuts.LIBRE && it.date!! > LocalDateTime.now() }

        val springUser = auth.principal as User
        val utilisateur = utilisateurRepository.findOneByEmail(springUser.username)

        model.addAttribute("reservation", Reservation())
        model.addAttribute("coursId", coursId)
        model.addAttribute("creneaux", creneaux)
        model.addAttribute("utilisateur", utilisateur)

        return "reservation/create"
    }

    @PostMapping("cours/{coursId}/reserver")
    fun createReservation(@Valid reservation: Reservation, @PathVariable coursId: Long, auth: Authentication, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "redirect:/cours/$coursId/reserver"
        }

        val springUser = auth.principal as User
        val utilisateur = utilisateurRepository.findOneByEmail(springUser.username).get()

        reservation.creneau?.let {
            val mCreneau = Creneau(
                    statuts = CreneauStatuts.OCCUPEE,
                    utilisateur = utilisateur,
                    date = it.date,
                    cours = it.cours
            )
            creneauRepository.deleteById(it.id)
            creneauRepository.save(mCreneau)
            val reservation = reservationRepository.save(reservation.copy(utilisateur = utilisateur, creneau = mCreneau))
            val paiement = Paiement(
                    montant = reservation.creneau?.cours?.prix ?: 0.0,
                    reservation = reservation,
                    createdAt = Instant.now()
            )
            paiementRepository.save(paiement)

        }

        return "redirect:/cours/$coursId/reserver"
    }

    @GetMapping("reservations/{id}")
    fun getReservationById(@PathVariable id: Long, model: Model): Any? {
        return reservationRepository.findById(id)
                .orElseThrow { ChangeSetPersister.NotFoundException() }
        return "reservation"
    }

}
