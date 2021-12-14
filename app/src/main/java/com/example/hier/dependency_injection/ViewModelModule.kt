package com.example.hier.dependency_injection

//import com.example.hier.ui.rooms.LocationViewModel
import com.example.hier.ui.coworking.ALCViewModel
import com.example.hier.ui.coworking.CoworkingRecapViewModel
import com.example.hier.ui.login.LoginViewModel
import com.example.hier.ui.makeChoice.ChoiceViewModel
import com.example.hier.ui.profile.ProfileViewModel
import com.example.hier.ui.reservations.ReservationsViewModel
import com.example.hier.ui.room.RoomViewModel
import com.example.hier.ui.roomoverview.RoomOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RoomOverviewViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { ReservationsViewModel(get()) }
    viewModel { ChoiceViewModel(get()) }
    viewModel { RoomViewModel(get()) }
    viewModel { ALCViewModel(get()) }
    viewModel { CoworkingRecapViewModel(get()) }
    //viewModel { RoomViewModel(get()) }
    // viewModel { LocationViewModel(get()) }
}
