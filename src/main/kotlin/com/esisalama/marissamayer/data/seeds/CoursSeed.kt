package com.esisalama.marissamayer.data.seeds

import com.esisalama.marissamayer.data.entity.Cours
import com.esisalama.marissamayer.data.repository.CoursRepository
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import com.github.javafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class CoursSeed(
        @Autowired private val coursRepository: CoursRepository,
        @Autowired private val utilisateurRepository: UtilisateurRepository
) : CommandLineRunner {

    private val faker = Faker()

    override fun run(vararg args: String?) {
        val courses = coursRepository.findAll()

        val utilisateur = utilisateurRepository.findById(1L)

        if (courses.isEmpty() && utilisateur.isPresent) {
            for (i in 1..5) {
                val cours = Cours(
                        nom = faker.funnyName().name(),
                        prix = faker.number().randomDouble(2, 100, 1000),
                        description = faker.lorem().characters(100),
                        duree = faker.number().numberBetween(5, 30),
                        prerequis = faker.name().title(),
                        createdAt = Instant.now(),
                        instructeur = utilisateur.get()
                )

                coursRepository.save(cours)
            }
        }
    }
}