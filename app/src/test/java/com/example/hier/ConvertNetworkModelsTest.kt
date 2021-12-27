package com.example.hier

import com.example.hier.networkModels.MeetingRoomNetworkModel
import com.example.hier.networkModels.UserNetworkModel
import org.junit.Test
import kotlin.test.assertEquals

class ConvertNetworkModelsTest {

    @Test
    fun convertRoomNetworkModelToRoom() {
        var meetingRoomNetworkModel =
            MeetingRoomNetworkModel(
                1, "testRoom", "testroom.jpeg",
                10, 1, 50.0, 100.0,
                75.5, 20.0
            )
        var room = meetingRoomNetworkModel.toDatabaseModel()

        assertEquals(meetingRoomNetworkModel.id, room.id)
        assertEquals(meetingRoomNetworkModel.name, room.name)
        assertEquals(meetingRoomNetworkModel.imageName, room.imageName)
        assertEquals(meetingRoomNetworkModel.numberOfSeats, room.numberOfSeats)
        assertEquals(meetingRoomNetworkModel.locationId, room.locationId)
        assertEquals(meetingRoomNetworkModel.priceEvening, room.priceEvening)
        assertEquals(meetingRoomNetworkModel.priceFullDay, room.priceFullDay)
        assertEquals(meetingRoomNetworkModel.priceHalfDay, room.priceHalfDay)
        assertEquals(meetingRoomNetworkModel.priceTwoHours, room.priceTwoHours)
    }

    @Test
    fun convertUserNetworkModelToUser() {
        var userNetworkModel = UserNetworkModel(
            1, "Thomas", "Boghaert",
            "thomas.boghaert@student.hogent.be", "thomas.boghaert@student.hogent.be",
            "BE123456789", "0412345678"
        )
        var user = userNetworkModel.toDatabaseModel()

        assertEquals(userNetworkModel.customerId, user.userId)
    }
}