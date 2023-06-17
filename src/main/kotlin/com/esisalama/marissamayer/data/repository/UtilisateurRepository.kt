package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Utilisateur
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UtilisateurRepository : JpaRepository<Utilisateur, Long> {
    fun findOneByEmail(email: String): Optional<Utilisateur>
    fun findOneByNom(nom: String): Optional<Utilisateur>
}