package com.example.rest4peace.Data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//import androidx.room.Room
//import com.example.rest4peace.Data.Rooms

data class Hotel(
   @StringRes val name: Int,
    @DrawableRes val imageUrl: Int,
    @StringRes val aboutThisHotel:Int,
    @StringRes val rating:Int,
//    val rooms: List<Rooms>
)


