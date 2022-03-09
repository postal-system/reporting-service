package io.aimc.reportingservice.repository

import io.aimc.reportingservice.entity.Portion
import io.aimc.reportingservice.model.custom.ICountPortion
import java.time.LocalDate
import java.util.Optional
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PortionRepository : JpaRepository<Portion, UUID> {
    @Query(
        "SELECT count(id) as portionAmount, coalesce(sum(cardinality(letter_ids)),0) as letterAmount FROM portion where sending_date BETWEEN TO_DATE(:date, 'YYYY-MM-DD') AND TO_DATE(:date, 'YYYY-MM-DD')  + 1",
        nativeQuery = true
    )
    fun countDate(@Param("date") date: LocalDate): List<ICountPortion>
}
