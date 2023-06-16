package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.entity.Categorie
import com.esisalama.marissamayer.data.repository.CategorieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/categories")
class CategorieController(
        @Autowired private val categorieRepository: CategorieRepository
) {
    @GetMapping
    fun getAllCategories(model: Model): String {
        val categories = categorieRepository.findAll()
        model.addAttribute("categories", categories)
        return "categories/list"
    }

    @GetMapping("/{id}")
    fun getCategorieById(@PathVariable id: Long, model: Model): String {
        val categorie = categorieRepository.findById(id)
                .orElseThrow { Exception("Categorie not found with id: $id") }
        model.addAttribute("categorie", categorie)
        return "categories/details"
    }

    @GetMapping("/new")
    fun showCreateForm(model: Model): String {
//        model.addAttribute("categorie", Categorie())
        return "categories/create"
    }

    @PostMapping
    fun createCategorie(
            @ModelAttribute("categorie") categorie: Categorie,
            bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "categories/create"
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
