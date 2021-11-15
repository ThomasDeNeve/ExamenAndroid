package com.example.hier.network

import com.example.hier.models.LocationWithRooms
import com.example.hier.util.Resource
import com.example.hier.util.Status
import com.example.hier.util.parseJson

class RemoteDataSource(val apiService: ApiService) : BaseDataSource() {
    //suspend fun getRooms() = getResult { apiService.getRooms() }
    suspend fun loginUser(username: String, password: String) =
        getResult { apiService.loginUser(username, password) }

    suspend fun getReservations() = getResult { apiService.getReservations() }

    suspend fun getLocations() = getResult { apiService.getLocations() }

    fun getLocations2(): Resource<ArrayList<LocationWithRooms>> {
        //TODO: this is hardcoded to mock a API response for testing
        val jsonString =
            "[{\"id\": 1, \"name\": \"HIER\", \"street\": \"Hier ergens\", \"number\": 1, \"postalCode\": \"BE9000\", \"place\": \"Gent\", \"meetingRooms\": [ {\"id\": 1, \"name\": \"HIER.Boven\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 4, \"name\": \"HIER.Ginder\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 7, \"name\": \"HIER.Vanvoor\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null } ], \"coWorkSeats\": [] }, { \"id\": 2, \"name\": \"Kluizen\", \"street\": \"Ergens anders\", \"number\": 1, \"postalCode\": \"BE9300\", \"place\": \"Aalst\", \"meetingRooms\": [ { \"id\": 2, \"name\": \"The Practice\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 3, \"name\": \"Boardroom\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 5, \"name\": \"The Executive Room\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null }, { \"id\": 6, \"name\": \"The Course\", \"numberOfSeats\": 0, \"seats\": [], \"price\": null } ], \"coWorkSeats\": [] }]"

        val newJsonString = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"HIER\",\n" +
                "    \"street\": \"Hier ergens\",\n" +
                "    \"number\": 1,\n" +
                "    \"postalCode\": \"BE9000\",\n" +
                "    \"place\": \"Gent\",\n" +
                "    \"meetingRooms\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"HIER.Boven\",\n" +
                "        \"numberOfSeats\": 0,\n" +
                "        \"seats\": [],\n" +
                "        \"priceEvening\": 175,\n" +
                "        \"priceFullDay\": 250,\n" +
                "        \"priceHalfDay\": 145,\n" +
                "        \"priceTwoHours\": 80\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"HIER.Ginder\",\n" +
                "        \"numberOfSeats\": 0,\n" +
                "        \"seats\": [],\n" +
                "        \"priceEvening\": 325,\n" +
                "        \"priceFullDay\": 395,\n" +
                "        \"priceHalfDay\": 275,\n" +
                "        \"priceTwoHours\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"HIER.Vanvoor\",\n" +
                "        \"numberOfSeats\": 0,\n" +
                "        \"seats\": [],\n" +
                "        \"priceEvening\": 175,\n" +
                "        \"priceFullDay\": 250,\n" +
                "        \"priceHalfDay\": 145,\n" +
                "        \"priceTwoHours\": 80\n" +
                "      }\n" +
                "    ],\n" +
                "    \"coWorkRooms\": []\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Kluizen\",\n" +
                "    \"street\": \"Ergens anders\",\n" +
                "    \"number\": 1,\n" +
                "    \"postalCode\": \"BE9300\",\n" +
                "    \"place\": \"Aalst\",\n" +
                "    \"meetingRooms\": [\n" +
                "      {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"The Executive Room\",\n" +
                "        \"numberOfSeats\": 0,\n" +
                "        \"seats\": [],\n" +
                "        \"priceEvening\": 175,\n" +
                "        \"priceFullDay\": 250,\n" +
                "        \"priceHalfDay\": 145,\n" +
                "        \"priceTwoHours\": 80\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 5,\n" +
                "        \"name\": \"Boardroom\",\n" +
                "        \"numberOfSeats\": 0,\n" +
                "        \"seats\": [],\n" +
                "        \"priceEvening\": 295,\n" +
                "        \"priceFullDay\": 355,\n" +
                "        \"priceHalfDay\": 225,\n" +
                "        \"priceTwoHours\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 6,\n" +
                "        \"name\": \"The Practice\",\n" +
                "        \"numberOfSeats\": 0,\n" +
                "        \"seats\": [],\n" +
                "        \"priceEvening\": 375,\n" +
                "        \"priceFullDay\": 455,\n" +
                "        \"priceHalfDay\": 325,\n" +
                "        \"priceTwoHours\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 7,\n" +
                "        \"name\": \"The Course\",\n" +
                "        \"numberOfSeats\": 0,\n" +
                "        \"seats\": [],\n" +
                "        \"priceEvening\": 400,\n" +
                "        \"priceFullDay\": 550,\n" +
                "        \"priceHalfDay\": 375,\n" +
                "        \"priceTwoHours\": 0\n" +
                "      }\n" +
                "    ],\n" +
                "    \"coWorkRooms\": []\n" +
                "  }\n" +
                "]"
        val locations = parseJson(newJsonString)
        return Resource(Status.SUCCESS, locations, null)
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