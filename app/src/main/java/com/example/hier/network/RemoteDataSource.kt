package com.example.hier.network
import com.example.hier.MyApplication
import com.example.hier.MyApplication.Companion.apiAccessToken

import com.example.hier.networkModels.CoworkReservationPostModel
import com.example.hier.networkModels.MeetingroomReservationPostModel
import com.example.hier.util.Resource

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getReservations(date: String) = getResult { apiService.getCoworkReservations(date) }

    suspend fun getAvailableMeetingrooms(
        neededseats: Int,
        locationid: Int,
        datetimeStart: String,
        datetimeEnd: String
    ) = getResult {
        apiService.getAvailableMeetingrooms(
            neededseats,
            locationid,
            datetimeStart,
            datetimeEnd
        )
    }

    suspend fun addCoworkReservation(coworkReservationPostModel: CoworkReservationPostModel): Resource<String> {
        return getResult { apiService.postCoworkReservation(coworkReservationPostModel) }
    }

    suspend fun addMeetingroomReservation(meetingroomReservationPostModel: MeetingroomReservationPostModel) =
        getResult { apiService.postMeetingroomReservation(meetingroomReservationPostModel) }
}
