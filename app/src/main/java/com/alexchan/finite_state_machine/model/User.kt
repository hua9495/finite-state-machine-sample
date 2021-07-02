package com.alexchan.finite_state_machine.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class User(
    @SerializedName("id") var id: String = UUID.randomUUID().toString(),
    @SerializedName("username") var userName: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("profile_image") var profileImage: ProfileImage? = null
)
