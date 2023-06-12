package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Creneau
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreneauRepository : JpaRepository<Creneau, Long>
