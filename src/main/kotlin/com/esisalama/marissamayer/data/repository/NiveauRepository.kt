package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Niveau
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NiveauRepository : JpaRepository<Niveau, Long>