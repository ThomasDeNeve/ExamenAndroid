package com.example.hier.repository

import com.example.hier.database.LocalDataSource
import com.example.hier.models.LocationWithRooms
import com.example.hier.network.RemoteDataSource
import com.example.hier.util.Resource
import com.example.hier.util.Status
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocationRepository(val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource) {
    fun getLocations(): Resource<List<LocationWithRooms>> {
        var gson: Gson = Gson()
        var jsonString = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"HIER\",\n" +
                "        \"street\": \"Hier ergens\",\n" +
                "        \"number\": 1,\n" +
                "        \"postalCode\": \"BE9000\",\n" +
                "        \"place\": \"Gent\",\n" +
                "        \"meetingRooms\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"HIER.Boven\",\n" +
                "                \"numberOfSeats\": 0,\n" +
                "                \"seats\": [],\n" +
                "                \"price\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 4,\n" +
                "                \"name\": \"HIER.Ginder\",\n" +
                "                \"numberOfSeats\": 0,\n" +
                "                \"seats\": [],\n" +
                "                \"price\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 7,\n" +
                "                \"name\": \"HIER.Vanvoor\",\n" +
                "                \"numberOfSeats\": 0,\n" +
                "                \"seats\": [],\n" +
                "                \"price\": null\n" +
                "            }\n" +
                "        ],\n" +
                "        \"coWorkSeats\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Kluizen\",\n" +
                "        \"street\": \"Ergens anders\",\n" +
                "        \"number\": 1,\n" +
                "        \"postalCode\": \"BE9300\",\n" +
                "        \"place\": \"Aalst\",\n" +
                "        \"meetingRooms\": [\n" +
                "            {\n" +
                "                \"id\": 2,\n" +
                "                \"name\": \"The Practice\",\n" +
                "                \"numberOfSeats\": 0,\n" +
                "                \"seats\": [],\n" +
                "                \"price\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 3,\n" +
                "                \"name\": \"Boardroom\",\n" +
                "                \"numberOfSeats\": 0,\n" +
                "                \"seats\": [],\n" +
                "                \"price\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 5,\n" +
                "                \"name\": \"The Executive Room\",\n" +
                "                \"numberOfSeats\": 0,\n" +
                "                \"seats\": [],\n" +
                "                \"price\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 6,\n" +
                "                \"name\": \"The Course\",\n" +
                "                \"numberOfSeats\": 0,\n" +
                "                \"seats\": [],\n" +
                "                \"price\": null\n" +
                "            }\n" +
                "        ],\n" +
                "        \"coWorkSeats\": []\n" +
                "    }\n" +
                "]"


        val locationArray = Gson().fromJson(jsonString, Array<LocationWithRooms>::class.java)//.toList()

        /*val typeToken = object : TypeToken<List<LocationWithRooms>>() {}.type
        val locationArray = Gson().fromJson<List<LocationWithRooms>>(jsonString, typeToken)*/

        return Resource(Status.ERROR, null, null) //TODO fix
        //return Resource(Status.ERROR, locationArray, null)

    }
}