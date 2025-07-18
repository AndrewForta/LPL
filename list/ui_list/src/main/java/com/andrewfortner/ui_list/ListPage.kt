package com.andrewfortner.ui_list

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.andrewfortner.ui_list.components.ListItem
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun ListPage(
    state: ListPageState,
    imagePickerFlow: MutableSharedFlow<Uri>,
    events: (ListPageEvents) -> Unit,
    iconClicked: () -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(null) {
        events(ListPageEvents.Setup(
            launchPicker = iconClicked,
            imagePickerFlow = imagePickerFlow,
            context = context
        ))
    }

    // Given more time I would add pagination to the list
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(items = state.postList ?: listOf(), key = { it.id }) { post ->
            ListItem(
                post = post,
                iconClicked = {
                    events(ListPageEvents.IconClicked(it))
                },
            )
            HorizontalDivider()

        }
    }
}