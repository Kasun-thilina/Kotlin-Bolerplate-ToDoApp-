package com.kc.todoapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DTodo(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    var completed: Boolean?,
):Parcelable