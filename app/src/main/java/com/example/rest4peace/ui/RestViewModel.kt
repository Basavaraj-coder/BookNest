package com.example.rest4peace.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rest4peace.ui.UIState.RestUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//inherit viewmodel class from package
class RestViewModel : ViewModel() {
    //(_ underscore here means internal of variable within the class)
    //also used private for to securing outside of this class no once can't access _UiState
    private val _UiState = MutableStateFlow(RestUiState());

    /*
    MutableStateFlow: This is a class from the Kotlin Coroutines library that represents a state holder with an observable flow of values. It's commonly used in Jetpack Compose to manage UI state and trigger recomposition when the state changes.
RestUiState(): This likely refers to a custom class or data class that you've defined to represent the state of your UI. It's being used to initialize the MutableStateFlow with an initial state value.
In a Nutshell:
This line of code creates a private, read-only reference named _UiState that holds a MutableStateFlow object. This MutableStateFlow is initialized with an instance of your custom RestUiState class, representing the initial state of your UI.
Typical Usage in Jetpack Compose:
You would typically expose a read-only StateFlow property based on this private MutableStateFlow to allow other parts of your Compose UI to observe state changes and recompose accordingly.
     */
    val UiState: StateFlow<RestUiState> = _UiState.asStateFlow() // read only,writing is not allowed
    //writing can be achieved by _UiState in this same class only
    /***
     * Note-
     * Use StateFlow: When you want to expose state to the outside world but ensure it cannot be modified externally. This is typically done to maintain encapsulation and prevent unintended side effects.
     * Use MutableStateFlow: When you need to manage and update state internally within a class or a function. This is typically used by the owner of the state.
     * *******/
    //now here we need to update the value of RestUiState variable, here we also need to update the state
//    Lodge Details
    private val _lodgeRating = MutableStateFlow(0)
    val lodgeRating: StateFlow<Int> = _lodgeRating.asStateFlow()

    private val _aboutLodge = MutableStateFlow(0)
    val aboutlodge: StateFlow<Int> = _aboutLodge.asStateFlow()

    private val _LodgeImage = MutableStateFlow(0)
    val lodgeImage: StateFlow<Int> = _LodgeImage.asStateFlow()

    //    Room Details------------------
    private val _roomName = MutableStateFlow(0)
    val roomName: StateFlow<Int> = _roomName.asStateFlow()

    private val _roomPrice = MutableStateFlow(0)
    val roomPrice: StateFlow<Int> = _roomPrice.asStateFlow()

    private val _roomstatus = MutableStateFlow(mutableMapOf<Int, String>())
    val roomStatus: StateFlow<MutableMap<Int, String>> get() = _roomstatus.asStateFlow()

    private val _roomsSelected = MutableStateFlow(0)
    val roomsSelected: StateFlow<Int> = _roomsSelected.asStateFlow()
    //----------------------------------------------------------------------------------------

    private val _daysBooked = MutableStateFlow(1)
    val daysBooked: StateFlow<Int> = _daysBooked.asStateFlow()

    private val _totalCost = MutableStateFlow(0.0)
    val totalCost: StateFlow<Double> = _totalCost.asStateFlow()

    //
    // State to manage the visibility of the bottom sheet
    val _isBottomSheetVisible = MutableStateFlow(false)
    val isBottomSheetVisible: StateFlow<Boolean> = _isBottomSheetVisible

    lateinit var updatingStateofClickedLodgeInHotelListScreen: Job
    lateinit var updatingStateofClickedLodgeRoomsInHotelListScreen: Job

    fun updateSelectedLodgeCategory(
        lodgeName: Int,
        lodgeRating: Int,
        abouthisHotel: Int,
        lodgeImage: Int,
    ) { //updating all records of specific lodge
//        _lodgeSelectedCategory.value = category
        updatingStateofClickedLodgeInHotelListScreen = viewModelScope.launch(Dispatchers.Main) {
            try {
                _UiState.update {
                    it.copy(selectedLodgeCardClicked = lodgeName)
                }
                _lodgeRating.value = lodgeRating
                _aboutLodge.value = abouthisHotel
                _LodgeImage.value = lodgeImage
            } catch (e: Exception) {
                print("Exception in viewmodel in updateSelectedLodgeCategory() ${e.printStackTrace()}")
            }
        }
    }

    fun updateRoomDetails(roomName: Int, hotelName: Int, roomStatus: String, count: Int) {
        updatingStateofClickedLodgeRoomsInHotelListScreen =
            viewModelScope.launch(Dispatchers.Main) {

                try {
                    _roomName.value = roomName
                    _UiState.update {
                        it.copy(lodgeNameAfterRoomSelection = hotelName)
                    }
                } catch (e: Exception) {
                    print("Exception in viewmodel in updateRoomName() ${e.printStackTrace()}")
                }
            }
        updateRoomStatus(roomName, roomStatus, count = count)
    }

    private fun updateRoomStatus(roomName: Int, roomStatus: String, count: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _roomstatus.update { currentStatusMap ->
                val updatedMap = currentStatusMap.toMutableMap()  // Create a mutable copy
                updatedMap[roomName] = roomStatus  // Update the status of the specific room
                // Check if any room is still "Selected"
                val isAnyRoomSelected = updatedMap.values.any { it == "Selected" } //_isBottomSheetVisible.value
                _isBottomSheetVisible.value = isAnyRoomSelected
                updateRoomsSelected(count =_roomsSelected.value + count)
                updatedMap  // Return the Map with the updated status
            }
        }
    }

    fun updateRoomsSelected(count: Int) {
        _roomsSelected.value = count
        _isBottomSheetVisible.value = count > 0
    }

//    fun updateBottomSheetVisibility() {
//        val shouldShowBottomSheet = roomStatus.value.values.any { it == "Selected" }
//        _isBottomSheetVisible.value = shouldShowBottomSheet
//    }
//    fun toggleBottomSheetVisibility(isVisible: Boolean) {
//        _isBottomSheetVisible.value = isVisible
//    }

    fun updateDaysBooked(days: Int) {
        _daysBooked.value = days
    }

    fun updateTotalCost(cost: Double) {
        var sum = _totalCost.value
        sum += cost
        _totalCost.value = sum
    }

}