package com.example.rest4peace.ui.UIState

import androidx.annotation.DrawableRes
import com.example.rest4peace.Data.DataSource

//State Collection: restUiState collects the current state from the ViewModel.

data class RestUiState(
    val selectedLodgeCardClicked: Int = 0,
    val lodgeNameAfterRoomSelection: Int = 0,
)