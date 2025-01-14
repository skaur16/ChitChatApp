package com.example.chitchatapp.data

import com.example.chitchatapp.domain.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepo {

        suspend fun saveUser(user : User){

                Firebase.firestore
                        .collection("users")
                        .add(user)
                        .await()
        }


}
