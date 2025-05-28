package com.example.chitchatapp.domain.models

import com.google.firebase.firestore.DocumentId

data class Channel(
    @DocumentId
    val id : String? = null,
    val imageUrl : String?,
    val type : ChannelType,
    val name : String,
    val description : String?,
    val members : List<String>,
    val messages : List<Message>
){
    constructor() : this(null, "", ChannelType.OneToOne,"", "", emptyList(), emptyList())
    enum class ChannelType { OneToOne, Group}
}
