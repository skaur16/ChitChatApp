package com.example.chitchatapp.data.remote

import com.example.chitchatapp.data.remote.FirestoreCollections.channelsColl
import com.example.chitchatapp.domain.models.Channel
import com.example.chitchatapp.domain.models.Message
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
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

    suspend fun getAllChannelsOf(userId : String) : List<Channel>{
        return Firebase.firestore
            .channelsColl()
            .whereEqualTo(Channel::type.name, Channel.ChannelType.OneToOne)
            .whereArrayContains(Channel::members.name, userId)
            .get()
            .await()
            .toObjects(Channel::class.java)
    }

    suspend fun getChannel(channelId : String) : Channel{
        return Firebase.firestore
            .channelsColl()
            .document(channelId)
            .get()
            .await()
            .toObject(Channel::class.java)
            ?: error("No channel found with id")
    }

    suspend fun sendMessages(channelId : String, message : Message){
        Firebase.firestore
            .channelsColl()
            .document(channelId)
            .update(Channel::messages.name, FieldValue.arrayUnion(message))
            .await()
    }

    suspend fun subscribeToChannel(channelId : String) : Flow<Channel>{
        return callbackFlow{

            val registration = Firebase.firestore
                .channelsColl()
                .document(channelId)
                .addSnapshotListener{snapshot, error->
                    error?.let{
                        throw it
                    }
                    val channel = snapshot?.toObject(Channel::class.java)
                    channel?.let{
                        CoroutineScope(coroutineContext).launch{
                            send(it)
                        }
                    }
                }

            awaitClose {  registration.remove() }
        }
    }
}