package com.esisalama.marissamayer.data.seeds

import com.esisalama.marissamayer.data.entity.Cours
import com.esisalama.marissamayer.data.entity.Niveau
import com.esisalama.marissamayer.data.repository.CoursRepository
import com.esisalama.marissamayer.data.repository.NiveauRepository
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import com.github.javafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class CoursSeed(
        @Autowired private val coursRepository: CoursRepository,
        @Autowired private val utilisateurRepository: UtilisateurRepository,
        @Autowired private val niveauRepository: NiveauRepository,
) : CommandLineRunner {

    private val faker = Faker()

    override fun run(vararg args: String?) {
        val courses = coursRepository.findAll()

        val utilisateur = utilisateurRepository.findById(1L)

        val niveaux = niveauRepository.findAll()

        val niveauxList = mutableListOf<Niveau>()

        if (niveaux.isEmpty()) {
            val niveau1 = niveauRepository.save(Niveau(nom = "Débutant"))
            val niveau2 = niveauRepository.save(Niveau(nom = "Intermédiaire"))
            val niveau3 = niveauRepository.save(Niveau(nom = "Avancé"))
            niveauxList.addAll(listOf(niveau1, niveau2, niveau3))
        } else {
            niveauxList.addAll(niveaux)
        }

//        if (courses.isEmpty() && utilisateur.isPresent) {
//            for (i in 1..5) {
//                val cours = Cours(
//                        nom = faker.funnyName().name(),
//                        prix = faker.number().randomDouble(2, 100, 1000),
//                        description = faker.lorem().characters(100),
//                        duree = faker.number().numberBetween(5, 30),
//                        prerequis = faker.name().title(),
//                        createdAt = Instant.now(),
//                        instructeur = utilisateur.get(),
//                        niveau = niveauxList.random()
//                )
//
//                coursRepository.save(cours)
//            }
//        }
    }
}