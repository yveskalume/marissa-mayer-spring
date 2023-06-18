package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Categorie
import com.esisalama.marissamayer.data.entity.Cours
import com.esisalama.marissamayer.data.repository.CategorieRepository
import com.esisalama.marissamayer.data.repository.CoursRepository
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/cours")
class CoursController(
        @Autowired private val coursRepository: CoursRepository,
        @Autowired private val categorieRepository: CategorieRepository,
        @Autowired private val utilisateurRepository: UtilisateurRepository
) {
    @GetMapping
    fun getAllCours(model: Model): String {
        val courses = coursRepository.findAll()
        model.addAttribute("courses", courses)
        return "cours/index"
    }

    @GetMapping("/{id}")
    fun getCoursById(@PathVariable id: Long, model: Model): String {
        val cours = coursRepository.findById(id)
                .orElseThrow { Exception("Cours not found with id: $id") }

        val suggestionCours = cours.categorie?.let { coursRepository.findByCategorieId(it.id) } ?: listOf()

        model.addAttribute("cours", cours)
        model.addAttribute("suggestionCours", suggestionCours)
        return "cours/details"
    }

    @GetMapping("/new")
    fun showCreateForm(model: Model): String {
        val cours = Cours()
        val categories = categorieRepository.findAll()

        model.addAttribute("cours", cours)
        model.addAttribute("categories", categories)

        return "instructor/create_cours"
    }

    @PostMapping("/new")
    fun createCours(
            @Valid cours: Cours,
            bindingResult: BindingResult,
            auth: Authentication,
            model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.allErrors)
            return "instructor/create_cours"
        }

        val springUser = auth.principal as User
        val utilisateur = utilisateurRepository.findOneByEmail(springUser.username)

        if (utilisateur.isPresent) {
            val mCours = cours.copy(
                    instructeur = utilisateur.get()
            )
            coursRepository.save(mCours)
        }
        return "redirect:/instructor/my-courses"
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
