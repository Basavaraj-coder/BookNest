package com.example.rest4peace.Data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Rooms(
    @StringRes val hotelName: Int, //
    @StringRes val roomName: Int,
    val roomBookedStatus:String,
    val price:Int,
    @DrawableRes val roomImage:Int
)
