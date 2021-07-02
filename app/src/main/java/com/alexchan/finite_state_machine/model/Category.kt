package com.alexchan.finite_state_machine.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Category(
    @SerializedName("id") var id: String = UUID.randomUUID().toString(),
    @SerializedName("title") var title: String? = null
)