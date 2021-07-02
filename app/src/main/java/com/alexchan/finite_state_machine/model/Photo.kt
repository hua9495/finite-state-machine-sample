package com.alexchan.finite_state_machine.model

import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID

data class Photo(
    @SerializedName("id") var id: String = UUID.randomUUID().toString(),
    @SerializedName("created_at") var createdAt: Date = Date(),
    @SerializedName("updated_at") var updatedAt: Date = Date(),
    @SerializedName("width") var width: Int = 0,
    @SerializedName("height") var height: Int = 0,
    @SerializedName("color") var color: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("user") var user: User? = null,
    @SerializedName("urls") var urls: Urls? = null,
    @SerializedName("categories") var categories: List<Category>? = null,
    @SerializedName("links") var links: Links? = null,
    @SerializedName("views") var views: Int? = null,
    @SerializedName("downloads") var downloads: Int? = null,
    @SerializedName("sponsorship") var sponsorship: Sponsorship? = null,
    var favoritedDate: Date = Date()
)