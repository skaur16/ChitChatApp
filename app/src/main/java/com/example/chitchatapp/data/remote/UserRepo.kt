package com.example.chitchatapp.data.remote

import com.example.chitchatapp.data.remote.FirestoreCollections.usersColl
import com.example.chitchatapp.domain.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await

class UserRepo {

        suspend fun saveUser(user : User){
                Firebase.firestore
                        .usersColl()
                        .add(user)
                        .await()
        }

        suspend fun getUserWithEmail(email : String) : User?{
                return Firebase.firestore
                        .usersColl()
                        .whereEqualTo(User::email.name, email)
                        .get()
                        .await()
                        .toObjects(User::class.java)
                        .firstOrNull()
        }

}
