package io.aimc.reportingservice.repository

import io.aimc.reportingservice.entity.Portion
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PortionRepository : JpaRepository<Portion, UUID>
