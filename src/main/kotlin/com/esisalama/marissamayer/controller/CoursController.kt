package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Cours
import com.esisalama.marissamayer.data.entity.CreneauStatuts
import com.esisalama.marissamayer.data.repository.*
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@Controller
@RequestMapping("/cours")
class CoursController(
        @Autowired private val coursRepository: CoursRepository,
        @Autowired private val categorieRepository: CategorieRepository,
        @Autowired private val utilisateurRepository: UtilisateurRepository,
        @Autowired private val niveauRepository: NiveauRepository,
        @Autowired private val creneauRepository: CreneauRepository
) {
    @GetMapping
    fun getAllCours(@RequestParam(required = false, name = "categorie") categorieId: Long?, model: Model): String {

        val courses = if (categorieId != null) {
            coursRepository.findByCategorieId(categorieId)
        } else {
            coursRepository.findAll()
        }
        model.addAttribute("courses", courses)
        return "cours/index"
    }

    @GetMapping("/{id}")
    fun getCoursById(@PathVariable id: Long, model: Model): String {
        val cours = coursRepository.findById(id)
                .orElseThrow { Exception("Cours not found with id: $id") }

        val suggestionCours = cours.categorie?.let { coursRepository.findByCategorieId(it.id) } ?: listOf()

        val creneaux = creneauRepository.findAllByCoursId(id).filter { it.statuts == CreneauStatuts.LIBRE && it.date!! > LocalDateTime.now() }

        model.addAttribute("cours", cours)
        model.addAttribute("suggestionCours", suggestionCours)
        model.addAttribute("creneaux", creneaux)
        return "cours/details"
    }

    @GetMapping("/new")
    fun showCreateForm(model: Model): String {
        val cours = Cours()
        val categories = categorieRepository.findAll()
        val niveaux = niveauRepository.findAll()
        model.addAttribute("cours", cours)
        model.addAttribute("categories", categories)
        model.addAttribute("niveaux", niveaux)
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


    @GetMapping("/{id}/delete")
    fun deleteCours(@PathVariable id: Long): String {
        val cours = coursRepository.findById(id)
                .orElseThrow { Exception("Cours not found with id: $id") }
        coursRepository.delete(cours)
        return "redirect:/cours"
    }
}
