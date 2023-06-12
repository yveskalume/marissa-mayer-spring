package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Cours
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CoursRepository : JpaRepository<Cours, Long>