package com.example.hier.network

import android.util.Log
import com.example.hier.models.LocationWithRooms
import com.example.hier.models.User
import com.example.hier.util.Resource
import com.example.hier.util.Status
import com.example.hier.util.parseJson
import retrofit2.Response

class RemoteDataSource(val apiService: ApiService) : BaseDataSource() {
    suspend fun getRooms() = getResult { apiService.getRooms() }
    suspend fun loginUser(username: String, password: String) = getResult { apiService.loginUser(username, password) }
    suspend fun getReservations() = getResult { apiService.getReservations() }

    fun getLocations(): Resource<ArrayList<LocationWithRooms>> {
        //TODO: this is hardcoded to mock a API response for testing
        val jsonString =
            "[{\"id\": 1, \"name\": \"HIER\", \"street\": \"Hier ergens\", \"number\": 1, \"postalCode\": \"BE9000\", \"place\": \"Gent\", \"meetingRooms\": [ {\"id\": 1, \"name\": \"HIER.Boven\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 4, \"name\": \"HIER.Ginder\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 7, \"name\": \"HIER.Vanvoor\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null } ], \"coWorkSeats\": [] }, { \"id\": 2, \"name\": \"Kluizen\", \"street\": \"Ergens anders\", \"number\": 1, \"postalCode\": \"BE9300\", \"place\": \"Aalst\", \"meetingRooms\": [ { \"id\": 2, \"name\": \"The Practice\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 3, \"name\": \"Boardroom\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 5, \"name\": \"The Executive Room\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 6, \"name\": \"The Course\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null } ], \"coWorkSeats\": [] }]"
        val locations = parseJson(jsonString)
        return Resource(Status.ERROR, locations, null)
    }

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