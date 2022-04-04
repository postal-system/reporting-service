package io.codero.reportingservice.domain

import io.codero.reportingservice.domain.model.IReportPortion
import io.codero.reportingservice.domain.model.Portion
import java.time.LocalDate
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PortionRepository : JpaRepository<Portion, UUID> {
    //    @Query(
//        "SELECT " +
//                "count(id) as portionAmount, " +
//                "coalesce(sum(cardinality(letter_ids)),0) as letterAmount " +
//                "FROM portion " +
//                "where sending_date BETWEEN TO_DATE(:start, 'YYYY-MM-DD') AND TO_DATE(:end, 'YYYY-MM-DD')",
//        nativeQuery = true
//    )
//    fun countByTime(@Param("start") start: LocalDate, @Param("end") end: LocalDate): Optional<ICountPortion>
//
    @Query(
        "SELECT * " +
                "FROM portion " +
                "where sending_date BETWEEN TO_DATE(:start, 'YYYY-MM-DD') AND TO_DATE(:end, 'YYYY-MM-DD')",
        nativeQuery = true
    )
    fun getByTimes(@Param("start") start: LocalDate, @Param("end") end: LocalDate): List<Portion>

    @Query(
        "SELECT " +
                "count(id) as portionAmount, " +
                "coalesce(sum(cardinality(letter_ids)),0) as letterAmount, " +
                "Date(sending_date) as sendingDate " +
                "FROM portion " +
                "where sending_date BETWEEN TO_DATE(:start, 'YYYY-MM-DD') AND TO_DATE(:end, 'YYYY-MM-DD') " +
                "group by sendingDate;",
        nativeQuery = true
    )
    fun getReportPortion(@Param("start") start: LocalDate, @Param("end") end: LocalDate): List<IReportPortion>
}
