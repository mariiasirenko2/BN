package com.example.bn.dto

import java.time.LocalDateTime

data class SlotData(
    val id: Long,
    val serviceId: Long,
    val masterId: Long,
    val from: LocalDateTime,
    val to: LocalDateTime,
   // val status: Status
)
