package com.example.rest4peace.Data

import androidx.compose.ui.res.stringResource
import com.example.rest4peace.R

object DataSource {
//this file object class is to initialise object once with values
    fun loadHotels():List<Hotel>{
        val hotelLst = mutableListOf<Hotel>()

        hotelLst.add(
            Hotel(
                name = R.string.Angraj_Hotel,//hotelname string res
                imageUrl =R.drawable.hotel_angraj,//imageres/drawableres
                aboutThisHotel = R.string.About_angraj_hotel,
                rating = R.string.Angraj_hotel_rating,
//                rooms = listOf(
//                   // hotelname stringres
//                    //roomName stringres
//                    //price stringres
//                    Rooms(R.string.Angraj_Hotel,"Deluxe AC","select",3550,R.drawable.deluxe_ac_room_angraj),
//                    Rooms(R.string.Angraj_Hotel,"Normal 2 bed","select",1550,R.drawable.normal_two_bed_room_angraj),
//                )
            )
        )
        hotelLst.add(
            Hotel(
                name = R.string.Tirupati,//hotelname string res
                imageUrl =R.drawable.tirupati_hotel,//imageres/drawableres
                aboutThisHotel = R.string.About_Tirupati_hotel,
                rating = R.string.Tirupati_hotel_rating,
//                rooms = listOf(
//                    // hotelname stringres
//                    //roomName stringres
//                    //price stringres
//                    Rooms(R.string.Tirupati,"Deluxe Room AC","select",3560,R.drawable.deluxe_room_ac_tirupati),
//                    Rooms(R.string.Tirupati,"Double Room","select",10000,R.drawable.double_room_astha),
//                )
            )
        )
        hotelLst.add(
            Hotel(
                name = R.string.Astha_lodge,//hotelname string res
                imageUrl =R.drawable.astha_lodge,//imageres/drawableres
                aboutThisHotel = R.string.About_Astha_hotel,
                rating = R.string.Astha_hotel_rating,
//                rooms = listOf(
//                    // hotelname stringres
//                    //roomName stringres
//                    //price stringres
//                    Rooms(R.string.Astha_lodge,"Double Room","Select",2000,R.drawable.double_room_astha),
//                    Rooms(R.string.Astha_lodge,"Executive Room","select",3000,R.drawable.executive_room_astha),
//                )
            )
        )

        hotelLst.add(
            Hotel(
                name = R.string.Saurabh_Lodge,//hotelname string res
                imageUrl =R.drawable.saurabh_lodge,//imageres/drawableres
                aboutThisHotel = R.string.About_Saurabh_hotel,
                rating = R.string.Saurabh_hotel_rating,

            )
        )
        hotelLst.add(
            Hotel(
                name = R.string.West_lodge,//hotelname string res
                imageUrl =R.drawable.saurabh_lodge,//imageres/drawableres
                aboutThisHotel = R.string.About_West_hotel,
                rating = R.string.West_hotel_rating,
            )
        )
        return hotelLst
    }

    fun loadRooms(hotelName:Int):List<Rooms> = mutableListOf<Rooms>(
        Rooms(R.string.Angraj_Hotel,R.string.Deluxe_AC,"select",3550,R.drawable.deluxe_ac_room_angraj),
        Rooms(R.string.Angraj_Hotel,R.string.Normal_2_bed,"select",1550,R.drawable.normal_two_bed_room_angraj),
        Rooms(R.string.Angraj_Hotel,R.string.Doormatory,"select",200,R.drawable.deluxe_ac_room_angraj),
        Rooms(R.string.Tirupati,R.string.Deluxe_Room_AC,"select",3560,R.drawable.deluxe_room_ac_tirupati),
        Rooms(R.string.Tirupati,R.string.Double_Room,"select",10000,R.drawable.double_room_astha),
        Rooms(R.string.Astha_lodge,R.string.Double_Room,"Select",2000,R.drawable.double_room_astha),
        Rooms(R.string.Astha_lodge,R.string.Executive_Room,"select",3000,R.drawable.executive_room_astha),
        Rooms(R.string.Saurabh_Lodge,R.string.Deluxe_AC,"select",3550,R.drawable.deluxe_ac_room_angraj),
        Rooms(R.string.Saurabh_Lodge,R.string.Normal_2_bed,"select",1550,R.drawable.normal_two_bed_room_angraj),
        Rooms(R.string.West_lodge,R.string.Deluxe_Room_AC,"select",3560,R.drawable.deluxe_room_ac_tirupati),
        Rooms(R.string.West_lodge,R.string.Double_Room,"select",10000,R.drawable.double_room_astha),
    ).filter {
        it.hotelName == hotelName
    }
}

//Loading rooms
