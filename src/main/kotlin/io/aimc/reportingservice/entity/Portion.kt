package io.aimc.reportingservice.entity

import com.vladmihalcea.hibernate.type.array.ListArrayType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType::class)
data class Portion(
    @Id
    @Column(name = "portion_id", columnDefinition = "pg-uuid")
    private val id: UUID,
    @Type(type = "list-array")
    @Column(name = "shipment_ids", columnDefinition = "uuid[]")
    private val shipmentIds: MutableList<UUID>,
    @Column(name = "sending_date", nullable = false)
    private val date: LocalDate
)
