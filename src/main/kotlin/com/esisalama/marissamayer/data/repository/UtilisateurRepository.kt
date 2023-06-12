package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Utilisateur
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UtilisateurRepository : JpaRepository<Utilisateur, Long>