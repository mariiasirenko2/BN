package com.example.bn.api

import com.example.bn.RegistrationRequestDto
import com.example.bn.dto.BookingRequestDto
import com.example.bn.dto.ClientProfile
import com.example.bn.dto.EditSlotRequestBody
import com.example.bn.dto.MasterArrayDto
import com.example.bn.dto.MasterServiceArrayDto
import com.example.bn.dto.PublicSlotDto
import com.example.bn.dto.PublicSlotsMapDto
import com.example.bn.dto.ServiceListDto
import com.example.bn.dto.SlotDto
import com.example.bn.dto.SlotRequestDto
import com.example.bn.dto.SlotStatus
import com.example.bn.dto.SlotsMapDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/me")
    suspend fun getMe(@Header("Authorization") token: String): Response<ClientProfile>

    @POST("/authenticate")
    suspend fun authenticate(@Body token: String): Response<ResponseBody>

    @POST("/me")
    suspend fun setRole(
        @Body registrationRequestDto: RegistrationRequestDto,
        @Header("Authorization") token: String
    ): Response<ResponseBody>

    @GET("/services")
    suspend fun getAllServices(@Header("Authorization") token: String): Response<ServiceListDto>

    @GET("/masters")
    suspend fun getAllMasters(
        @Header("Authorization") token: String,
        @Query("country") country: String? = null,
        @Query("region") region: String? = null,
        @Query("city") city: String? = null,
        @Query("types") types: List<Long>? = null,
        @Query("services") services: List<Long>? = null
    ): Response<MasterArrayDto>

    @GET("/masters/{masterId}/services")
    suspend fun getMasterServicesByMasterId(
        @Header("Authorization") token: String,
        @Path("masterId") masterId: Long
    ): Response<MasterServiceArrayDto>

    @GET("/masters/slots")
    suspend fun getSlotsForRange(
        @Header("Authorization") token: String,
        @Query("startDateTime") startDateTime: String? = null,
        @Query("endDateTime") endDateTime: String? = null,
        @Query("services") services: List<Long>? = null,
        @Query("masters") masters: List<Long>?  = null
    ): Response<PublicSlotsMapDto>

    @GET("/me/slots")
    suspend fun getAllMySlots(
        @Header("Authorization") token: String,
        @Query("startDateTime") startDateTime: String? = null,
        @Query("endDateTime") endDateTime: String? = null,
        @Query("status") status: SlotStatus? = null
    ): Response<SlotsMapDto>

    @POST("/me/slots")
    suspend fun addNewSlot(
        @Header("Authorization") token: String,
        @Body slotRequestDto: SlotRequestDto
    ): Response<SlotDto>

    @PATCH("/me/slots/{slotId}")
    suspend fun editSlot(
        @Path("slotId") slotId: Long,
        @Body editSlotRequestBody: EditSlotRequestBody,
        @Header("Authorization") token: String
    ): Response<SlotDto>

    @PATCH("/masters/slots/{slotId}")
    suspend fun sendBookingRequest(
        @Path("slotId") slotId: Long,
        @Body bookingRequestDto: BookingRequestDto,
        @Header("Authorization") token: String
    ): Response<PublicSlotDto>
}
