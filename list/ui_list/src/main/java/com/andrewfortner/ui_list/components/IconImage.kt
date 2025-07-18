package com.andrewfortner.ui_list.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconImage(
    icon: Bitmap?,
    tint: Color = Color.White,
    onClick: (() -> Unit)? = null,
    modifier: Modifier,
    maxHeight: Dp = 50.dp
) {
    val backgroundColor = Color.Black
    if(icon != null) {
        if(onClick != null) {
            Button(
                modifier = Modifier
                    .padding(all = 0.dp)
                    .heightIn(max = maxHeight)
                    .clip(CircleShape)
                    .then(modifier),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    onClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    bitmap = icon.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxHeight()
                )
            }
        } else {
            Image(
                bitmap = icon.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .heightIn(max = maxHeight)
                    .aspectRatio(1f),

                )
        }
    } else {
        if(onClick != null) {
            Button(
                modifier = Modifier
                    .padding(all = 0.dp)
                    .heightIn(max = maxHeight)
                    .clip(CircleShape)
                    .then(modifier),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    onClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxHeight(),
                        tint = tint,
                    )
                }

            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Icon",
                    modifier = Modifier
                        .heightIn(max = maxHeight)
                        .aspectRatio(1f),
                    tint = tint,
                )
            }
        }
    }
}