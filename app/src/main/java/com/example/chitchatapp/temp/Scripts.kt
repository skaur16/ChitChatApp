package com.example.chitchatapp.temp

import android.annotation.SuppressLint
import com.example.chitchatapp.data.remote.FirestoreCollections.usersColl
import com.example.chitchatapp.domain.models.Gender
import com.example.chitchatapp.domain.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

object Scripts {

    @SuppressLint("SuspiciousIndentation")
    suspend fun saveDummyUsers(){

        val sampleUsers = listOf(
            User(
                id = "1",
                name = "Alice Johnson",
                email = "alice.johnson@example.com",
                bio = "Love traveling and photography!",
                gender = Gender.Female,
                dob = "1990-05-21"
            ),
            User(
                id = "2",
                name = "Bob Smith",
                email = "bob.smith@example.com",
                bio = "Software engineer and coffee addict.",
                gender = Gender.Male,
                dob = "1988-09-15"
            ),
            User(
                id = "3",
                name = "Charlie Brown",
                email = "charlie.brown@example.com",
                bio = "Bookworm and aspiring writer.",
                gender = Gender.Male,
                dob = "1992-11-03"
            ),
            User(
                id = "4",
                name = "Daisy White",
                email = "daisy.white@example.com",
                bio = "Yoga enthusiast and dog lover.",
                gender = Gender.Female,
                dob = "1995-07-12"
            ),
            User(
                id = "5",
                name = "Ethan Clark",
                email = "ethan.clark@example.com",
                bio = "Tech geek and mountain climber.",
                gender = Gender.Male,
                dob = "1993-02-28"
            ),
            User(
                id = "6",
                name = "Fiona Green",
                email = "fiona.green@example.com",
                bio = "Passionate about art and design.",
                gender = Gender.Female,
                dob = "1991-12-08"
            ),
            User(
                id = "7",
                name = "George King",
                email = "george.king@example.com",
                bio = "Gamer and pizza connoisseur.",
                gender = Gender.Male,
                dob = "1989-03-19"
            ),
            User(
                id = "8",
                name = "Hannah Lee",
                email = "hannah.lee@example.com",
                bio = "Nature lover and aspiring chef.",
                gender = Gender.Female,
                dob = "1994-06-05"
            ),
            User(
                id = "9",
                name = "Ian Wright",
                email = "ian.wright@example.com",
                bio = "Cyclist and coffee shop explorer.",
                gender = Gender.Male,
                dob = "1990-10-17"
            ),
            User(
                id = "10",
                name = "Jasmine Patel",
                email = "jasmine.patel@example.com",
                bio = "Music enthusiast and traveler.",
                gender = Gender.Female,
                dob = "1996-01-22"
            )
        )

    val collRef = Firebase.firestore.usersColl()
    val batch = Firebase.firestore.batch()

        sampleUsers.forEach {user->

        batch.set(
            collRef.document(collRef.document().id),
            user
        )
        }
        batch.commit().await()
    }

}