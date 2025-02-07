package com.example.chitchatapp.data.remote

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreCollections {

    fun FirebaseFirestore.usersColl() = collection("users")


}