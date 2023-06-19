package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.repository.CoursRepository
import com.esisalama.marissamayer.data.repository.NiveauRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class InstructorController(
        @Autowired private val coursRepository: CoursRepository,
) {

    @GetMapping(value = ["/instructor/my-courses"], name = "instructor.my-courses")
    fun myCourses(auth: Authentication, model: Model): String {
        val user = auth.principal as User
        val courses = user.username?.let { coursRepository.findByInstructeurEmail(it) } ?: listOf()
        model.addAttribute("courses", courses)

        return "instructor/my_courses"
    }
}