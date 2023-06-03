package com.example.bn.dto

data class PublicMasterProfileDto(
    val id: Long = 0,
    val country: String = "",
    val region: String = "",
    val city: String = "",
    val address: String = "",
    val name: String = "",
    val surname: String = "",
    val image: String? = null,
    val types: List<MasterTypeDto> = emptyList(),
    val phoneNumber: String = "",
    val email: String = ""
)

