package com.example.junemon.kotlinnetworking.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class AllPlayer(val player: List<Player>) {
    @Parcelize
    class Player(var idPlayer: String?, var strPlayer: String?, var strNationality: String?,
                 var strTeam: String?, var strDescriptionEN: String?, var strThumb: String?,
                 var strCutout: String?, var strPosition: String?) : Parcelable {

    }
}