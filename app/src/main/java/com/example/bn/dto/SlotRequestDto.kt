package com.example.bn.dto

import java.time.ZonedDateTime

data class SlotRequestDto(
    val from: ZonedDateTime,

    val to: ZonedDateTime
)