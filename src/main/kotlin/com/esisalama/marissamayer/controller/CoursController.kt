package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.repository.CategorieRepository
import com.esisalama.marissamayer.data.repository.CoursRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/cours")
class CoursController(
        @Autowired private val coursRepository: CoursRepository,
        @Autowired private val categorieRepository: CategorieRepository
) {
    @GetMapping
    fun getAllCours(model: Model): String {
        val coursList = coursRepository.findAll()
        model.addAttribute("coursList", coursList)
        return "cours/list"
    }

    @GetMapping("/{id}")
    fun getCoursById(@PathVariable id: Long, model: Model): String {
        val cours = coursRepository.findById(id)
                .orElseThrow { Exception("Cours not found with id: $id") }
        model.addAttribute("cours", cours)
        return "cours/details"
    }

    @GetMapping("/new")
    fun showCreateForm(model: Model): String {
        val categories = categorieRepository.findAll()
        model.addAttribute("categories", categories)
        return "cours/create"
    }

    @PostMapping
    fun createCours(
            bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            val categories = categorieRepository.findAll()
//            model.addAttribute("categories", categories)
            return "cours/create"
        }
//        coursRepository.save(cours)
        return "redirect:/cours"
    }

    @GetMapping("/{id}/edit")
    fun showUpdateForm(@PathVariable id: Long, model: Model): String {
        val cours = coursRepository.findById(id)
                .orElseThrow { Exception("Cours not found with id: $id") }
        val categories = categorieRepository.findAll()
        model.addAttribute("cours", cours)
        model.addAttribute("categories", categories)
        return "cours/edit"
    }

    @PostMapping("/{id}")
    fun updateCours(
            @PathVariable id: Long,
            bindingResult: BindingResult,
            model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            val categories = categorieRepository.findAll()
            model.addAttribute("categories", categories)
            return "cours/edit"
        }
        val cours = coursRepository.findById(id)
                .orElseThrow { Exception("Cours not found with id: $id") }
//        cours.nom = updatedCours.nom
//        cours.description = updatedCours.description
//        cours.duree = updatedCours.duree
//        cours.prerequis = updatedCours.prerequis
//        cours.categorie = updatedCours.categorie
        coursRepository.save(cours)
        return "redirect:/cours"
    }

    @GetMapping("/{id}/delete")
    fun deleteCours(@PathVariable id: Long): String {
        val cours = coursRepository.findById(id)
                .orElseThrow { Exception("Cours not found with id: $id") }
        coursRepository.delete(cours)
        return "redirect:/cours"
    }
}
