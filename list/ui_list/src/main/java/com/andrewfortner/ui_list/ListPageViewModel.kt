package com.andrewfortner.ui_list

import android.graphics.ImageDecoder
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewfortner.domain.Post
import com.andrewfortner.interactors.getPosts
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListPageViewModel : ViewModel() {

    private val _state = MutableStateFlow(ListPageState())
    val state: StateFlow<ListPageState> = _state.asStateFlow()

    private var imagePickerJob: Job? = null

    // Handle UI events here
    fun onEvent(event: ListPageEvents) {
        when (event) {
            is ListPageEvents.Setup -> {
                updateScheduleList()
                _state.update {
                    it.copy(context = event.context)
                }
                if(imagePickerJob != null) imagePickerJob?.cancel()
                imagePickerJob = viewModelScope.launch {
                    event.imagePickerFlow.collect {
                        updateSelectedIconUri(it)
                    }
                }
                viewModelScope.launch {
                    launchPicker =  { event.launchPicker() }
                }
            }
            is ListPageEvents.IconClicked -> {
                iconClicked(event.post)
            }
        }
    }

    private fun iconClicked(post: Post) {
        _state.update {
            it.copy(selectedPost = post)
        }
        launchPicker()
    }

    private var launchPicker: () -> Unit = {}

    private fun updateScheduleList() {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
            withContext(IO) {
                val schedule = getPosts()
                _state.update {
                    it.copy(
                        postList = schedule,
                        loading = false)
                }
            }
        }
    }

    fun updateSelectedIconUri(uri: Uri) {
        _state.update {
            it.copy(postList = _state.value.postList?.map {
                if (it.id == _state.value.selectedPost?.id) {
                    it.copy(iconImageFileName = uri.path ?: "")
                    try {
                        if (!uri.path.isNullOrEmpty() && state.value.context != null && state.value.selectedPost != null) {
                            val source = ImageDecoder.decodeBitmap(
                                ImageDecoder
                                    .createSource(state.value.context!!.contentResolver, uri)
                            )

                            val fileName = System.currentTimeMillis().toString()

                            ImageStorageManager.saveToInternalStorage(
                                state.value.context!!,
                                source,
                                fileName
                            )

                            val imageAsString = ImageStorageManager.bitMapToString(
                                ImageStorageManager.getImageFromInternalStorage(
                                    state.value.context!!,
                                    imageFileName = fileName
                                )
                            )
                            state.value.selectedPost!!.copy(
                                iconImageFileName = fileName,
                                iconImageAsString = imageAsString ?: "",)
                        } else {
                            it
                        }
                    } catch(e: Exception) {
                        e.printStackTrace()
                        it
                    }
                } else {
                    it
                }
            })
        }
        _state.update {
            it.copy(selectedPost = null)
        }
    }
}