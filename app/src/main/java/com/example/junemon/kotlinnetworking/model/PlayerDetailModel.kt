package com.example.junemon.kotlinnetworking.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class PlayerDetailModel(var players: List<Player>) {
    @Parcelize
    class Player(var strPlayer: String?, var strHeight: String?, var strWeight: String?,
                 var strPosition: String?, var strDescriptionEN: String?, var strThumb: String?) : Parcelable {

    }
}