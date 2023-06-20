package com.esisalama.marissamayer.controller

import com.esisalama.marissamayer.data.repository.CoursRepository
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class HomeController(
        @Autowired private val userDetailsService: UserDetailsService,
        @Autowired private val coursRepository: CoursRepository,
) : ErrorController {

    @GetMapping("/")
    fun index(): String {
        return "redirect:/cours"
    }

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest,model: Model): String? {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
        if (status != null) {
            model.addAttribute("statusCode",status)
        } else {
            model.addAttribute("statusCode","404")
        }
        return "error"
    }
}