package com.example.chitchatapp.domain.ext

import com.example.chitchatapp.domain.models.User

fun User.id() : String{
    return id ?:/* error("ID not found")*/ " "
}