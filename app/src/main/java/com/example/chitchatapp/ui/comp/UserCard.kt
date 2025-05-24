package com.example.chitchatapp.ui.comp

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chitchatapp.R
import com.example.chitchatapp.domain.models.Gender
import com.example.chitchatapp.domain.models.User
import com.example.chitchatapp.ui.theme.Neutral50

@Composable
fun UserCard(user : User) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "UserProfileImage",
            tint = Color.Gray,
            modifier = Modifier.size(48.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .padding(8.dp)
        )
        Column (){
            Text(text = user.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
                )
            Text(text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = Neutral50
                )

        }
    }
}

@Preview
@Composable
fun UserPreview() {
    UserCard(
        user = User(
            name = "Sharan",
            email = "me.sharandeepkaur16@gmail.com",
            dob = "16",
            bio = "Hellop",
            gender = Gender.Female
        )
    )
}