package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Paiement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaiementRepository : JpaRepository<Paiement, Long>