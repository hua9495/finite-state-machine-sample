package com.alexchan.finite_state_machine.model

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("html") var html: String? = null,
    @SerializedName("download") var download: String? = null
)