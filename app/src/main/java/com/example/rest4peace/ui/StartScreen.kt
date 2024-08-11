package com.example.rest4peace.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hotelInfoNRoomScreen

enum class Screens { //to dynamically display screen title on topAppBar nee to pass the title here
    hotelListScreen,
    RoomDetailScreen,
//    PhonenumberScreen("Verify Phone Number"),
//    Where2Go("Places You Can Visit"),
//    VisitingPlaceDescription("Visiting Place"),
//    FAQs("Frequently asked Questions"),
//    otpScreen("Verify OTP"),
//    paymentScreen("Payment"),
}

//if there is screen in behind current screen in backstack to check that we need a variable
var canNavigateBack = false

//to avoid any confusion we will be keeping all of our logic in same file that is in StartScreen file in one place
//rememberNavController create+remember the nav controller object
//navcontroller is responsible for navigation between routes
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Composable
fun startScreen(
    restViewModel: RestViewModel = RestViewModel(),
    navcontroller: NavHostController = rememberNavController()
) {
    //hotelListScreen(restviewModel = restViewModel)// navigation in this way is good for small projects for large project we need Navigation Composer
    canNavigateBack = navcontroller.previousBackStackEntry != null

    NavHost(navController = navcontroller, startDestination = Screens.hotelListScreen.name) {
        composable(route = Screens.hotelListScreen.name) { //used to create a route and destination
            hotelListScreen(restviewModel = restViewModel,
                onLodgeClicked = { lodgeName, lodgeRating, aboutThisHotel, lodgeImage ->
                    restViewModel.updateSelectedLodgeCategory(
                        lodgeName, lodgeRating, aboutThisHotel,
                        lodgeImage
                    )
                    try {
                        navcontroller.navigate(Screens.RoomDetailScreen.name)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            )
        }

        composable(route = Screens.RoomDetailScreen.name) { // this composable is for creating route and destination
            hotelInfoNRoomScreen(viewModel = restViewModel, onRoomsSelected = { it1, it2, it3, it4 ->
                restViewModel.updateRoomDetails(it1,it2,it3,it4)
                //restViewModel.updateRoomsSelected(it1)
            })
        }
    }
    /***
     * Explanation
     * navController: This is your NavController object, which manages navigation between different composables (screens) in your Jetpack Compose application.
     * .previousBackStackEntry: This property of the NavController gives you access to the NavBackStackEntry representing the screen that was active before the current one. It essentially peeks into the navigation history.
     * != null: This part checks if the previousBackStackEntry is not null. In other words, it checks if there actually is a previous screen to navigate back to.
     * canNavigateBack: This variable stores the result of the check (either true or false). It indicates whether or not you can navigate back in the navigation stack.
     * ****/
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = currentScreen.title,
//                        fontSize = 18.sp,
//                        fontFamily = FontFamily.SansSerif,
//                        fontWeight = FontWeight.SemiBold,
//                        color = Color.DarkGray
//                    )
//                },
//                navigationIcon = { //backIcon
//                    if (canNavigateBack) {
//                        IconButton(onClick = {
//                            navcontroller.navigateUp() // popup after clicking on back button screen gets poppedUp/removed
//                        }) {
//                            Icon(
//                                imageVector = Icons.Outlined.ArrowBack,
//                                contentDescription = "backbutton"
//                            )
//                        }//back icon should only be visible only if backstack is not empty,
//                        //what is backstack check out Readme file?
//                    }
//                }
//            )
//        },
//        bottomBar = {
//            //call another composable for bottom app bar for its design purpose
//            bottomAppBarDesign(navcontroller = navcontroller)
//        }
//    ) {
//        /******
//         * In Jetpack Compose, the Scaffold composable provides a flexible layout structure with slots for the top bar,
//         * bottom bar, floating action button, and content. The padding parameter in the Scaffold composable helps to
//         * manage the insets for the content, ensuring that the content does not overlap with the top bar, bottom bar,
//         * or other elements.
//         * ******/
//        //modifier = Modifier.padding(it)
//
//    }


    //This onLodgeCardClicked lambda will be responsible for = if user press on any hotel carditem
    // --so the user will navigate to hotelInfoNRoomScreen and also change in ViewModel

    //execution of this function
    //1) after clicking on card, onLodgeCardClicked executes
    //2) Then value gets changed into viewModel
    //3) Then depending upon value/state of widget it will navigate to screen,
    //4) why it is declared here in startScreen only becos everything we need in one file, so
    // we need to pass this lambda to hotelListScreen

    //also when user clicks any hotelcard, then to automatically navigate to hotelInfoRoomScreen
    //--need to implement this below code
//
//    hotelListScreen(restviewModel = restViewModel)

}

@Composable
fun bottomAppBarDesign(navcontroller: NavHostController) { // bottom navigation bar design
    Row(
        //also we have to ensure that there is sufficient space inside Row for three buttons
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {
                navcontroller.navigate(Screens.hotelListScreen.name) {
                    popUpTo(0)
                    //clearing all entries in backstack, this ensure that,
                    //-- users can't go back to previous screen, becos no
                    // screens are available, user is on default screen popUpTo(0) removes all entries in backstack
                }
            }
        ) {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "home icon bottom app bar")
            Text("Home", fontSize = 10.sp)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {
//                navcontroller.navigate(Screens.Where2Go.name) {
//                    popUpTo(0)
//                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "where 2 go icon bottom app bar"
            )
            Text("Where 2 Go", fontSize = 10.sp)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {
//                navcontroller.navigate(Screens.FAQs.name) {
//                    popUpTo(0)
//                }
            }
        ) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "FAQs icon bottom app bar")
            Text("FAQs", fontSize = 10.sp)
        }
    }
}
