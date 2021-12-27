package com.example.hier.network

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getUser(username: String) = getResult { apiService.getUser(username) }

    suspend fun getReservations() = getResult { apiService.getReservations() }

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

    suspend fun addReservation(reservationPostModel: ReservationPostModel) =
        getResult { apiService.addReservation(reservationPostModel) }

}
