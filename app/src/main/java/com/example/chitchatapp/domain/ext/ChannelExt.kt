package com.example.chitchatapp.domain.ext

import com.example.chitchatapp.domain.models.Channel

fun Channel.id() : String{
    return id ?: error("ID Not Found!")
}