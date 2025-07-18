package com.andrewfortner.ui_list.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectIconSimple(
    icon: Bitmap?,
    changeIcon: () -> Unit,
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Center,
        horizontalAlignment = Alignment.Start
    ) {
        IconImage(
            icon = icon,
            modifier = Modifier
                .aspectRatio(1f),
            onClick = {changeIcon()},
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}