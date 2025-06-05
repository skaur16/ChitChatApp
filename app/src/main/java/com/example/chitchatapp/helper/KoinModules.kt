package com.example.chitchatapp.helper

import com.example.chitchatapp.data.LocalRepo
import com.example.chitchatapp.data.remote.ChannelRepo
import com.example.chitchatapp.data.remote.UserRepo
import com.example.chitchatapp.feature.editProfile.EditProfileViewModel
import com.example.chitchatapp.feature.home.HomeViewModel
import com.example.chitchatapp.feature.login.LoginViewModel
import com.example.chitchatapp.feature.newChat.NewChatViewModel
import com.example.chitchatapp.feature.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { UserRepo() }
    single { LocalRepo(DataStoreUtil.create(get())) }
    single { ChannelRepo() }

}

val viewModelModule = module{

    viewModel { EditProfileViewModel(get(),get()) }
    viewModel { SplashViewModel(get())}
    viewModel { LoginViewModel(get(), get())}
    viewModel { NewChatViewModel(get(),get(), get())}
    viewModel { HomeViewModel(get(),get()) }
}