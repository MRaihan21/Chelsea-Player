package com.chelseaapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    var name: String = "",
    var description: String = "",
    var photo: Int = 0
) : Parcelable
