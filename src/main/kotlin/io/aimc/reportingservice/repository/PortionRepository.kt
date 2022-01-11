package io.aimc.reportingservice.repository

import io.aimc.reportingservice.entity.Portion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.util.*

interface PortionRepository : JpaRepository<Portion, UUID>, JpaSpecificationExecutor<Portion> {
    @Query(
        "select coalesce(sum(cardinality(shipment_ids)),0) from portion where sending_date = :date",
        nativeQuery = true
    )
    fun countShipmentByDate(@Param("date") date: LocalDate): Int

    @Query(
        "select count(portion_id) from portion where sending_date between :fromDate and date(:fromDate) + interval '1 week'",
        nativeQuery = true
    )
    fun countPortionByWeek(@Param("fromDate") fromDate: LocalDate): Int

    @Query(
        "select coalesce(sum(cardinality(shipment_ids)),0) from portion where sending_date between :fromDate and date(:fromDate) + interval '1 week'",
        nativeQuery = true
    )
    fun countShipmentByWeek(@Param("fromDate") fromDate: LocalDate): Int
}
