package com.example.chitchatapp



sealed class Screen(
    val route : String
){
    data object SPLASH : Screen("SPLASH")

    data object LOGIN : Screen("LOGIN")

    data object HOME : Screen("HOME")

    data object NewChat : Screen("NewChat")





    class EDITPROFILE(
        val email : String
    ) : Screen("EDITPROFILE?email=$email"){
        companion object{
            fun format() = "EDITPROFILE?email={email}"
        }
    }

    class Chat(
        val channelId : String
    ) : Screen("Chat?channelId=$channelId"){
        companion object{
            fun format() = "Chat?channelId={channelId}"
        }
    }
}
