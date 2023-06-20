package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Creneau
import com.esisalama.marissamayer.data.entity.CreneauStatuts
import com.esisalama.marissamayer.data.entity.Utilisateur
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CreneauRepository : JpaRepository<Creneau, Long> {
    fun findAllByCoursId(id: Long): List<Creneau>

    @Modifying
    @Query("UPDATE Creneau c set c.statuts = :status,c.utilisateur = :utilisateur WHERE c.id = :id")
    fun updateStatusAndUtilisateur(@Param(value = "id") id: Long, @Param(value = "status") status: CreneauStatuts, @Param(value = "utilisateur") utilisateur: Utilisateur)
}
