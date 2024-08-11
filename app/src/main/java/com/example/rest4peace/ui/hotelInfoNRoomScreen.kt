import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rest4peace.Data.DataSource
import com.example.rest4peace.Data.Rooms
import com.example.rest4peace.R
import com.example.rest4peace.ui.RestViewModel
import com.example.rest4peace.ui.UIState.RestUiState
import com.example.rest4peace.ui.theme.Purple40


enum class amenityIcons(val iconResId: Int) {
    GYM(R.drawable.gym),
    FreeParking(R.drawable.freeparking),
    Restaurant(R.drawable.restaraunt),
}

enum class IconText(val iconResId: String) {
    Gym("GYM"),
    FreeParking("Parking"),
    Restaurant("Restaurant"),
}

@Composable
fun hotelInfoNRoomScreen(
    viewModel: RestViewModel,
    onRoomsSelected: (Int, Int, String, Int) -> Unit
) {

    val uiState by viewModel.UiState.collectAsState()
    val hotelImage by viewModel.lodgeImage.collectAsState()
    val hotelRating by viewModel.lodgeRating.collectAsState()
    val aboutHotel by viewModel.aboutlodge.collectAsState()
    val isBottomSheetVisible by viewModel.isBottomSheetVisible.collectAsState() // Assuming you have this state to control visibility

    val listState =
        rememberLazyListState() // Create a LazyListState to store the state of list to make bottomsheet focusable
    LaunchedEffect(isBottomSheetVisible) {
        if (isBottomSheetVisible) {
            listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
        }
    }
//    --------------------------------------------------------------------------------------------------
    val ratingImage = when (hotelRating) {
        3 -> R.drawable.threestars
        4 -> R.drawable.fourstar
        else -> R.drawable.fivestars
    }

    val textLines = listOf(
        "Check in time: 12:00 PM and check out time: 11:00 AM",
        "Free Wifi",
        "Free Breakfast",
        "Free Cancellation",
        "Passport, Aadhar or any Govt. ID are accepted as ID proofs"
    )
    val bulletPoint = "\u2022"

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        state = listState // bottom sheet get focused,
    ) {
        item {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Purple40)
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .clip(RoundedCornerShape(bottomEnd = 40.dp)),
                painter = painterResource(id = hotelImage),
                contentDescription = "hotelImage",
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = stringResource(uiState.selectedLodgeCardClicked),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold, // Make the text bold
                color = Color(10, 100, 200, 255)
            )
            Image(
                modifier = Modifier
                    .width(70.dp)
                    .padding(start = 8.dp),
                painter = painterResource(id = ratingImage),
                contentDescription = "hotelImage",
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                text = "About This Hotel",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(10, 100, 200, 255)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = stringResource(aboutHotel),
                fontSize = 14.sp,
                color = Color(10, 100, 200, 255)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Amenities available",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(10, 100, 200, 255)
            )
            Divider(
                Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    amenityIcons.values().forEach { amenity ->
                        Image(
                            painter = painterResource(id = amenity.iconResId),
                            contentDescription = "Amenity Icon",
                            modifier = Modifier
                                .padding(5.dp)
                                .size(40.dp)
                        )
                    }
                }
//                Row( // for label below amenities icons
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 8.dp,),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    IconText.values().forEach { it ->
//                        Text(
//                            modifier = Modifier
//                                .width(60.dp).padding(end = 10.dp),
//                            text = it.name,
//                            fontSize = 8.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            color = Color(10, 100, 200, 255)
//                        )
//                    }
//                }
            }

            Divider(
                Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Property Rules & Information",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(10, 100, 200, 255)
            )
            textLines.forEach { lines ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = "$bulletPoint $lines",
                    fontSize = 14.sp,
                    color = Color(10, 100, 200, 255)
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Select Room(s)",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(10, 100, 200, 255)
            )
        }

        items(DataSource.loadRooms(uiState.selectedLodgeCardClicked)) { room ->
            RoomsCard(
                viewModel = viewModel,
                rooms = room,
                onRoomsSelected = onRoomsSelected
            )
        }

        if (isBottomSheetVisible) {
            item {
                Box {
                    BookingSummaryBottomSheet(viewModel = viewModel)
                }
            }
        }
    }
}

//this roomsCard() is to display widget/view of rooms here in select room screen
@Composable
fun RoomsCard(
    viewModel: RestViewModel,
    rooms: Rooms,
    onRoomsSelected: (Int, Int, String, Int) -> Unit
) {
    val context = LocalContext.current
    val roomStatus by viewModel.roomStatus.collectAsState()
    val currentRoomStatus = roomStatus[rooms.roomName] ?: "Select"

//     val RoomNameList by viewModel.roomName.collectAsState()
//    val RoomPriceList by viewModel.roomPrice.collectAsState()

    Log.d("LodgeName", "${stringResource(rooms.hotelName)}")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .border(
                width = 2.dp,
                color = Color(200, 200, 200, 200),
                shape = RoundedCornerShape(10.dp)
            )
            .shadow(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Purple40
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(200.dp)
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = rooms.roomImage),
                contentDescription = "room1 image",
                alignment = Alignment.Center
            )
            // Log.d("image in room", room.roomImage.toString())
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                //.background(Color.Yellow),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(rooms.roomName),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Text(
                        text = "${rooms.price} - Rs",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold, color = Color.White
                    )
                }
                Card(
                    //button select
                    modifier = Modifier
                        .height(50.dp)
                        .width(160.dp)
                        .border(
                            width = 2.dp,
                            color = Color(100, 100, 100, 155),
                            shape = RoundedCornerShape(10.dp),
                        ) //border to button select or card select
                        .shadow(2.dp)
                        .clickable {
                            val newStatus =
                                if (currentRoomStatus == "Select") "Selected" else "Select" // to check what and get status of room-selected
                            //println(roomStatus)
                            val countChange =
                                if (newStatus == "Selected") 1 else -1 // how many rooms are selected
                            onRoomsSelected(rooms.roomName, rooms.hotelName, newStatus, countChange)
                            // viewModel.updateRoomsSelected(viewModel.roomsSelected.value + countChange)
                            if (newStatus == "Selected") viewModel.updateTotalCost(rooms.price.toDouble()) //calculate/add each hotel price to VModel
                            else viewModel.updateTotalCost(-rooms.price.toDouble())

//                            Toast
//                                .makeText(context, "${rooms.roomName} $newStatus", Toast.LENGTH_SHORT)
//                                .show()
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(255, 255, 255, 200)
                    ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        //verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = currentRoomStatus, //restUiState.roomStatuses.getValue(roomName),
                            fontSize = 18.sp, color = Purple40,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )//select btn
                        //  Log.d("status", "${restUiState.roomStatuses.getValue(roomName)}")
                    }
                }
            }
        }
    }
}

@Composable
fun BookingSummaryBottomSheet(
    viewModel: RestViewModel
    // onCheckoutClick: () -> Unit // Callback for checkout button click
) {
    val roomsSelected by viewModel.roomsSelected.collectAsState() //get the state and value of roomsSelected
    val daysBooked by viewModel.daysBooked.collectAsState()
    val totalCost by viewModel.totalCost.collectAsState()

    Column(
        verticalArrangement = Arrangement.Bottom
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 5.dp),
            shape = RoundedCornerShape(40.dp),
            colors = CardDefaults.cardColors(containerColor = Purple40)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Rooms: $roomsSelected | ", color = Color.White)
                Text("Days: $daysBooked | ", color = Color.White)
                Text("Cost: ₹$totalCost", color = Color.White)
                Button(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(
                            255,
                            255,
                            255,
                            200
                        )
                    ),
                    onClick = {}) {
                    Text(
                        "Checkout",
                        fontSize = 14.sp, color = Purple40,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}





