package com.example.hier.network

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource()
{
    suspend fun loginUser(username: String, password: String) =
        getResult { apiService.loginUser(username, password) }

    suspend fun getReservations() = getResult { apiService.getReservations() }

    suspend fun getReservations(date: Long) = getResult { apiService.getReservations(date) }

    suspend fun getLocations() = getResult { apiService.getLocations() }

    suspend fun getAvailableMeetingrooms(neededseats:Int,locationid:Int,date:String) = getResult { apiService.getAvailableMeetingrooms(neededseats,locationid,date) }

    suspend fun addReservation(reservationPostModel: ReservationPostModel) = getResult { apiService.addReservation(reservationPostModel) }


    /*suspend fun reserveRoom(roomid: Int, customerId: Int) =
        getResult { apiService.reserveRoom(roomid, customerId)}*/

    /*suspend fun loginUser (username: String, password: String) : Resource<LoginResponse>{
        Log.e("RemoteDataSource", "got into loginUser")
        if (username == "admin" && password == "admin") {
            val loginres = LoginResponse(false, "", User());
            Log.e("RemoteDataSource", "Got into loginUser method")
            return Resource.success(loginres)
        } else {
            return Resource.error("fail")
        }
    }*/
}