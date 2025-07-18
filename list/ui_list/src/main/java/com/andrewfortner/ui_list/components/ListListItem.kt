package com.andrewfortner.ui_list.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andrewfortner.domain.Post
import com.andrewfortner.ui_list.ImageStorageManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(
    post: Post,
    iconClicked: (Post) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
            .clickable{}
            .padding(horizontal = 8.dp, vertical = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SelectIconSimple(
            icon = ImageStorageManager.stringToBitMap(post.iconImageAsString),
            changeIcon = {
                iconClicked(post)
            },
        )
        Spacer(Modifier.width(8.dp))
        Column(
            modifier = Modifier
        ) {
            Text(post.name)
            Spacer(Modifier.height(4.dp))
            Text(post.email)
            Spacer(Modifier.height(16.dp))
            Text(post.body)
        }
    }
}