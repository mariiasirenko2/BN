package com.example.bn.dto

import java.util.Date

class PublicSlotDto {
    val id: Long = 0
    val serviceId: Long = 0
    val masterId: Long = 0
    val from: Date = Date()
    val to: Date = Date()
    val status: SlotStatus = SlotStatus.AVAILABLE
}