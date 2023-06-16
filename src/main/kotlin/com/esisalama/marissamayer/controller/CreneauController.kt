package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.repository.CoursRepository
import com.esisalama.marissamayer.data.repository.CreneauRepository
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/creneaux")
class CreneauController(
        @Autowired private val creneauRepository: CreneauRepository,
        @Autowired private val coursRepository: CoursRepository,
        @Autowired private val utilisateurRepository: UtilisateurRepository
) {
    @GetMapping
    fun getAllCreneaux(model: Model): String {
        val creneauList = creneauRepository.findAll()
        model.addAttribute("creneauList", creneauList)
        return "creneaux/list"
    }

    @GetMapping("/{id}")
    fun getCreneauById(@PathVariable id: Long, model: Model): String {
        val creneau = creneauRepository.findById(id)
                .orElseThrow { Exception("Creneau not found with id: $id") }
        model.addAttribute("creneau", creneau)
        return "creneaux/details"
    }

    @GetMapping("/new")
    fun showCreateForm(model: Model): String {
        val coursList = coursRepository.findAll()
        val utilisateurList = utilisateurRepository.findAll()
        model.addAttribute("coursList", coursList)
        model.addAttribute("utilisateurList", utilisateurList)
        return "creneaux/create"
    }

    @PostMapping
    fun createCreneau(
            bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            val coursList = coursRepository.findAll()
            val utilisateurList = utilisateurRepository.findAll()
//            model.addAttribute("coursList", coursList)
//            model.addAttribute("utilisateurList", utilisateurList)
            return "creneaux/create"
        }
//        creneauRepository.save(creneau)
        return "redirect:/creneaux"
    }

    @GetMapping("/{id}/edit")
    fun showUpdateForm(@PathVariable id: Long, model: Model): String {
        val creneau = creneauRepository.findById(id)
                .orElseThrow { Exception("Creneau not found with id: $id") }
        val coursList = coursRepository.findAll()
        val utilisateurList = utilisateurRepository.findAll()
        model.addAttribute("creneau", creneau)
        model.addAttribute("coursList", coursList)
        model.addAttribute("utilisateurList", utilisateurList)
        return "creneaux/edit"
    }

    @PostMapping("/{id}")
    fun updateCreneau(
            @PathVariable id: Long,
            bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            val coursList = coursRepository.findAll()
            val utilisateurList = utilisateurRepository.findAll()
//            model.addAttribute("coursList", coursList)
//            model.addAttribute("utilisateurList", utilisateurList)
            return "creneaux/edit"
        }
        val creneau = creneauRepository.findById(id)
                .orElseThrow { Exception("Creneau not found with id: $id") }
//        creneau.jour = updatedCreneau.jour
//        creneau.heureDebut = updatedCreneau.heureDebut
//        creneau.heureFin = updatedCreneau.heureFin
//        creneau.statuts = updatedCreneau.statuts
//        creneau.cours = updatedCreneau.cours
//        creneau.utilisateur = updatedCreneau.utilisateur
        creneauRepository.save(creneau)
        return "redirect:/creneaux"
    }

    @GetMapping("/{id}/delete")
    fun deleteCreneau(@PathVariable id: Long): String {
        val creneau = creneauRepository.findById(id)
                .orElseThrow { Exception("Creneau not found with id: $id") }
        creneauRepository.delete(creneau)
        return "redirect:/creneaux"
    }
}
