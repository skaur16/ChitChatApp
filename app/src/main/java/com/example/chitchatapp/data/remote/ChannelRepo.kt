package com.example.chitchatapp.data.remote

import com.example.chitchatapp.data.remote.FirestoreCollections.channelsColl
import com.example.chitchatapp.domain.models.Channel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await

class ChannelRepo {

    suspend fun getOneToOneChannel(
        currentUserId : String,
        otherUserId : String
    ) : Channel? {
        return Firebase.firestore
            .channelsColl()
            .whereEqualTo(Channel::type.name, Channel.ChannelType.OneToOne)
            .whereArrayContainsAny(Channel::type.name, listOf(currentUserId, otherUserId))
            .get()
            .await()
            .toObjects(Channel::class.java)
            .firstOrNull {
                it.members == listOf(currentUserId, otherUserId)
            }
    }
}