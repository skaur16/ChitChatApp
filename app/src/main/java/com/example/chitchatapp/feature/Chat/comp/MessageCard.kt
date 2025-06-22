package com.example.chitchatapp.feature.Chat.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.example.chitchatapp.domain.models.Message
import com.example.chitchatapp.ui.theme.Neutral50
import com.streamliners.utils.DateTimeUtils
import java.text.SimpleDateFormat

@Composable
fun MessageCard(
    message : Message
) {


    val configuration = LocalConfiguration.current
    val userLocale = configuration.locales[0]

    Card(){

        Row(modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = message.message,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
                )

           /* val formattedTime = remember {
                derivedStateOf {
                    DateTimeUtils.formatTime(
                        DateTimeUtils.Format.HOUR_MIN_12,
                        message.time.toDate().time
                    )
                }
            }*/

            val sdf = SimpleDateFormat("hh:mm a", userLocale)
            val directFormattedTime = sdf.format(message.time.toDate())

            Text(
                text = directFormattedTime,
                style = MaterialTheme.typography.bodyMedium,
                color = Neutral50
            )
        }

    }
}