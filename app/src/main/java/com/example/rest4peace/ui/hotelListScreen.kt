package com.example.rest4peace.ui


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.rest4peace.Data.DataSource
import com.example.rest4peace.Data.Hotel
import com.example.rest4peace.ui.theme.Purple40
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun hotelListScreen(
    restviewModel: RestViewModel,
    //syntax for trailing lambda, function accepts string as argument and returns nothing
    onLodgeClicked: (Int, Int, Int, Int) -> Unit//lets pass this value to hotelCategory,becoz the clicked will be executed
// at the hotelCardComposable
) {
    //now here we need a variable which will check the Ui-state present in viewmodel
    val restUiState by restviewModel.UiState.collectAsState()
    //here collectAsState collect value from UiState and store it in restUiState
    val context = LocalContext.current
    LazyColumn {
//        item {
//            Text(text = restUiState.ClickStatus)
//        }
        items(DataSource.loadHotels()) {
            hotelCardCategory(
                context,
                it,
                restvModel = restviewModel,
                onLodgeClicked = onLodgeClicked
            )
        }
    }

}

@Composable
fun hotelCardCategory(
    context: Context,
    hotel: Hotel,
    restvModel: RestViewModel,
    onLodgeClicked: (Int, Int, Int, Int) -> Unit
    //param hotelname, hotelimage,hotelrating, room name, room price
) {
    val priceRange = mutableListOf<Int>()
  //  val lodgeRoomName = mutableListOf<Int>()
    // val lodgeName = stringResource(hotel.name)
    lateinit var conversionDataJob: Job

    DataSource.loadRooms(hotelName = hotel.name)
        .forEach { // logic to get the rooms list and its price
            priceRange.add(it.price)
           // lodgeRoomName.add(it.roomName)
            Log.d("price room ", "${it.price}")
        }

    val priceText = if (priceRange[0] > 0 && priceRange[1] > 0) {
        "Rs ${(priceRange[0])} To Rs ${priceRange[1]}"
    } else {
        "Sold Out"
    }
    var priceColor = if (priceRange.isNotEmpty()) Color.Black else Color.Red
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .clickable {
                onLodgeClicked(hotel.name, hotel.rating, hotel.aboutThisHotel, hotel.imageUrl)
            },

        colors = CardDefaults.cardColors(
            containerColor = Color(200, 200, 200, 100)
        )
    ) {
        Column {
            Image(
                painter = painterResource(hotel.imageUrl),
                contentDescription = "hotel lodge images",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
                    .clip(CutCornerShape(5.dp)),
                contentScale = ContentScale.Crop,
            )
            // Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 4.dp),
                //.background(Color.Yellow),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(hotel.name),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold
                )
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                        .shadow(2.dp, RoundedCornerShape(30.dp)),
                    colors = CardDefaults.cardColors(containerColor = Purple40)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(

                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    try {
                                        if (priceText == "Sold Out") {
                                            priceColor = Color.Red

                                        }
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }

                                },
                            text = priceText,
                            fontSize = 20.sp,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            color = priceColor,
                        )
                    }
                }
            }
        }
    }
}