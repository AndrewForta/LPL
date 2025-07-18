package com.andrewfortner.lpl

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrewfortner.lpl.ui.theme.LPLTheme
import com.andrewfortner.ui_list.ListPage
import com.andrewfortner.ui_list.ListPageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imagePickerFlow = MutableSharedFlow<Uri>(1)
        val pickMedia =
            this.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    CoroutineScope(Default).launch {
                        imagePickerFlow.emit(uri)
                    }
                    Log.d("PhotoPicker", "Selected URI: $uri")
                } else {
                    Log.d("PhotoPicker", "No media selected")

                }
            }

        val imagePicker = {
            pickMedia.launch("image/*")
        }

        enableEdgeToEdge()
        setContent {
            val viewModel = ListPageViewModel()
            LPLTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        ListPage(
                            state = viewModel.state.collectAsStateWithLifecycle().value,
                            events = viewModel::onEvent,
                            imagePickerFlow = imagePickerFlow,
                            iconClicked = {
                                imagePicker()
                            },
                        )
                    }
                }
            }
        }
    }
}