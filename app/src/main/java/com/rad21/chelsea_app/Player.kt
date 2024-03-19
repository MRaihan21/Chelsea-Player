package com.rad21.chelsea_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String,
    val desc: String,
    val photo: Int
) : Parcelable