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
}
