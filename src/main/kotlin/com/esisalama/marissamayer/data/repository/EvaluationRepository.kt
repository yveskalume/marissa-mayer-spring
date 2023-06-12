package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Evaluation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EvaluationRepository : JpaRepository<Evaluation, Long>