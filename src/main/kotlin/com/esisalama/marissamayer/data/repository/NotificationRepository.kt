package com.esisalama.marissamayer.data.repository

import com.esisalama.marissamayer.data.entity.Notification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository : JpaRepository<Notification, Long>