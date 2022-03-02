package io.aimc.reportingservice.repository

import io.aimc.reportingservice.entity.Portion
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PortionRepository : JpaRepository<Portion, UUID> {
    @Query(
        "select coalesce(sum(cardinality(letter_ids)),0) from portion where sending_date between :startTime and :endTime",
        nativeQuery = true
    )
    fun getCountLetterByBetweenTime(
        @Param("startTime") startTime: LocalDateTime,
        @Param("endTime") endTime: LocalDateTime
    ): Long

    @Query(
        "select count(id) from portion where sending_date between :startTime and :endTime",
        nativeQuery = true
    )
    fun getCountPortionByBetweenTime(
        @Param("startTime") startTime: LocalDateTime,
        @Param("endTime") endTime: LocalDateTime
    ): Long
}
