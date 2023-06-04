package com.example.bn.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

data class SlotRequestDto(
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val from: ZonedDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val to: ZonedDateTime
)