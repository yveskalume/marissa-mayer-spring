package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Categorie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategorieRepository : JpaRepository<Categorie, Long>