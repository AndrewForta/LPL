package com.andrewfortner.ui_list

import android.content.Context
import android.net.Uri
import com.andrewfortner.domain.Post
import kotlinx.coroutines.flow.MutableSharedFlow

sealed class ListPageEvents {
    // UI events go here
    data class Setup(
        val launchPicker: () -> Unit,
        val imagePickerFlow: MutableSharedFlow<Uri>,
        val context: Context? = null,
    ) : ListPageEvents()

    data class IconClicked(
        val post: Post,
    ) : ListPageEvents()

}