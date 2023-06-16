package com.esisalama.marissamayer.data.seeds

import com.esisalama.marissamayer.data.entity.Cours
import com.esisalama.marissamayer.data.repository.CoursRepository
import com.github.javafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class CoursSeed(
        @Autowired private val coursRepository: CoursRepository
) : CommandLineRunner {

    private val faker = Faker()

    override fun run(vararg args: String?) {
        val courses = coursRepository.findAll()
        if (courses.isEmpty()) {
            for (i in 1..5) {
                val cours = Cours(
                        nom = faker.funnyName().name(),
                        description = faker.lorem().characters(100),
                        duree = faker.number().numberBetween(5,30),
                        prerequis = faker.name().title(),
                        createdAt = Date()
                )
                coursRepository.save(cours)
            }
        }
    }
}