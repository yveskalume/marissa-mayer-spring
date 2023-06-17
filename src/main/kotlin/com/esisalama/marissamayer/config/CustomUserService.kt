package com.esisalama.marissamayer.config

import com.esisalama.marissamayer.data.repository.UtilisateurRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component("userDetailService")
class CustomUserService : UserDetailsService {

    @Autowired
    private lateinit var utilisateurRepository: UtilisateurRepository
    override fun loadUserByUsername(email: String): UserDetails {
        val optionalUtilisateur = utilisateurRepository.findOneByEmail(email)
        if (optionalUtilisateur.isEmpty) {
            throw Exception("Utilisateur non trouvé")
        }
        val utilisateur = optionalUtilisateur.get()

        val grantedAuthorities = listOf<GrantedAuthority>()

        return User(utilisateur.email,utilisateur.password,grantedAuthorities)
    }
}