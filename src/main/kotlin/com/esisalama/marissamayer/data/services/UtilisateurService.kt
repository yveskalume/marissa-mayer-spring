package com.esisalama.marissamayer.data.services

import com.esisalama.marissamayer.data.entity.Utilisateur
import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UtilisateurService {

    @Autowired
    private lateinit var utilisateurRepository: UtilisateurRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun save(utilisateur: Utilisateur): Utilisateur {
        return if (utilisateur.id == null) {
            val mUtilisateur = utilisateur.copy(password = passwordEncoder.encode(utilisateur.password), createdAt = Instant.now())
            utilisateurRepository.save(mUtilisateur)
        } else {
            utilisateurRepository.save(utilisateur)
        }
    }
}