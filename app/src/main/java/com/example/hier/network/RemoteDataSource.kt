package com.example.hier.network
import com.example.hier.MyApplication.Companion.apiAccessToken
import com.example.hier.networkModels.CoworkReservationPostModel
import com.example.hier.networkModels.MeetingroomReservationPostModel
import com.example.hier.util.Resource

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getUser(username: String) = getResult { apiService.getUser("Bearer $apiAccessToken", username) }

    suspend fun getReservations(date: String) = getResult { apiService.getCoworkReservations("Bearer $apiAccessToken", date) }

    suspend fun getAllReservations(customer: String, roomType: Int) = getResult { apiService.getAllReservations("Bearer $apiAccessToken", customer, roomType) }

    suspend fun getAvailableMeetingrooms(
        neededseats: Int,
        locationid: Int,
        datetimeStart: String,
        datetimeEnd: String
    ) = getResult {
        apiService.getAvailableMeetingrooms(
            "Bearer $apiAccessToken",
            neededseats,
            locationid,
            datetimeStart,
            datetimeEnd
        )
    }

    suspend fun addCoworkReservation(coworkReservationPostModel: CoworkReservationPostModel): Resource<String> {
        return getResult { apiService.postCoworkReservation("Bearer $apiAccessToken", coworkReservationPostModel) }
    }

    suspend fun addMeetingroomReservation(meetingroomReservationPostModel: MeetingroomReservationPostModel) =
        getResult { apiService.postMeetingroomReservation("Bearer $apiAccessToken", meetingroomReservationPostModel) }
}
