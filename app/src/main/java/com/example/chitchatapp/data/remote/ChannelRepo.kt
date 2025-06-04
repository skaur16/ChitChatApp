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
                it.members == listOf(currentUserId, otherUserId) ||
                        it.members == listOf(otherUserId, currentUserId)
            }
    }

    suspend fun createOneToOneChannel(
        currentUserId: String,
        otherUserId: String
    ) : String{

        val collRef = Firebase.firestore.channelsColl()
        val id = collRef.document().id

        collRef
            .document(id)
            .set(Channel(
                    imageUrl = null,
                    type = Channel.ChannelType.OneToOne,
                    name = "OneToOne",
                    description = null,
                    members = listOf(
                        currentUserId,
                        otherUserId
                    ),
                    messages = emptyList()
                )
            ).await()

        return id
    }
}