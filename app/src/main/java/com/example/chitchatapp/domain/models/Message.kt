package com.example.chitchatapp.domain.models

import com.google.firebase.Timestamp

data class Message(
    val time : Timestamp,
    val sender : String,
    val message : String,
    val mediaUrl : String?
){
    constructor() : this(Timestamp.now(), "", "", null)
}
