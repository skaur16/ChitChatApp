package com.example.chitchatapp.domain.models

import com.google.firebase.firestore.DocumentId

data class User(
    val id : String? = null,
    val name : String,
    val email : String,
    val bio : String,
    val gender : Gender,
    val dob : String? = null
) {
    constructor() : this(null, "","","",Gender.Male,null)




}
