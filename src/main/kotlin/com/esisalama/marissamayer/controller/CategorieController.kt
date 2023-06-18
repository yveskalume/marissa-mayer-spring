package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Categorie
import com.esisalama.marissamayer.data.repository.CategorieRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
class CategorieController(
        @Autowired private val categorieRepository: CategorieRepository
) {
    @GetMapping("/categories")
    fun getAllCategories(model: Model): String {
        val categories = categorieRepository.findAll()
        model.addAttribute("categories", categories)
        return "categorie/index"
    }

    @GetMapping("/categories/{id}")
    fun getCategorieById(@PathVariable id: Long, model: Model): String {
        val categorie = categorieRepository.findById(id)
                .orElseThrow { Exception("Categorie not found with id: $id") }
        model.addAttribute("categorie", categorie)
        return "categorie/details"
    }

    @GetMapping("/categories/new")
    fun showCreateForm(model: Model): String {
        model.addAttribute("categorie", Categorie())
        return "categorie/create"
    }

    @PostMapping("/categories/new")
    fun createCategorie(
            @Valid categorie: Categorie,
            bindingResult: BindingResult,
            model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.allErrors)
            return "categorie/create"
        }
        categorieRepository.save(categorie)
        return "redirect:/categories"
    }

    @GetMapping("/{id}/edit")
    fun showUpdateForm(@PathVariable id: Long, model: Model): String {
        val categorie = categorieRepository.findById(id)
                .orElseThrow { Exception("Categorie not found with id: $id") }
        model.addAttribute("categorie", categorie)
        return "categories/edit"
    }

    @PostMapping("/{id}")
    fun updateCategorie(
            @PathVariable id: Long,
            @ModelAttribute("categorie") updatedCategorie: Categorie,
            bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "categories/edit"
        }
        val categorie = categorieRepository.findById(id)
                .orElseThrow { Exception("Categorie not found with id: $id") }
        val newCategorie = categorie.copy(nom = updatedCategorie.nom, createdAt = updatedCategorie.createdAt)
        categorieRepository.save(newCategorie)

        return "redirect:/categories"
    }

    @GetMapping("/{id}/delete")
    fun deleteCategorie(@PathVariable id: Long): String {
        val categorie = categorieRepository.findById(id)
                .orElseThrow { Exception("Categorie not found with id: $id") }
        categorieRepository.delete(categorie)
        return "redirect:/categories"
    }
}
