package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Creneau
import com.esisalama.marissamayer.data.entity.CreneauStatuts
import com.esisalama.marissamayer.data.repository.CoursRepository
import com.esisalama.marissamayer.data.repository.CreneauRepository
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
class CreneauController(
        @Autowired private val creneauRepository: CreneauRepository,
        @Autowired private val coursRepository: CoursRepository,
        @Autowired private val utilisateurRepository: UtilisateurRepository
) {
    @GetMapping("cours/{coursId}/creneaux")
    fun getAllCreneaux(@PathVariable coursId: Long, model: Model): String {
        val cours = coursRepository.findById(coursId)
        val creneaux = creneauRepository.findAll()
        val statues = CreneauStatuts.values()
        val newCreneau = Creneau()
        if (cours.isEmpty) {
            throw Exception("Ce cours n'existe pas")
        }
        model.addAttribute("creneaux", creneaux)
        model.addAttribute("newCreneau", newCreneau)
        model.addAttribute("cours", cours.get())
        model.addAttribute("statues", statues)
        return "creneau/index"
    }

    @PostMapping("cours/{coursId}/creneaux")
    fun createCreneau(
            @Valid creneau: Creneau,
            @PathVariable coursId: Long,
            bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "redirect:/cours/$coursId/creneaux"
        }

        val cours = coursRepository.findById(coursId)

        if (cours.isPresent) {
            val mCreneau = creneau.copy(cours = cours.get())
            creneauRepository.save(mCreneau)
        }

        return "redirect:/cours/$coursId/creneaux"
    }

    @GetMapping("creneau/{id}/delete")
    fun deleteCreneau(@PathVariable id: Long): String {
        val creneau = creneauRepository.findById(id)
                .orElseThrow { Exception("Creneau not found with id: $id") }
        creneauRepository.delete(creneau)
        return "redirect:/creneaux"
    }
}
