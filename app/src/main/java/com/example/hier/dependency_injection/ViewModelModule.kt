package com.example.hier.dependency_injection

import com.example.hier.ui.login.LoginViewModel
import com.example.hier.ui.profile.ProfileViewModel
import com.example.hier.ui.reservations.ReservationsViewModel
import com.example.hier.ui.rooms.RoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RoomViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { ReservationsViewModel(get()) }
}
