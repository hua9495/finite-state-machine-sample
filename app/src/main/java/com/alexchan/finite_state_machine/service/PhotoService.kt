package com.alexchan.finite_state_machine.service

import com.alexchan.finite_state_machine.api.Client
import com.alexchan.finite_state_machine.core.network.NetworkRequestManager
import com.alexchan.finite_state_machine.core.state.Result
import com.alexchan.finite_state_machine.model.Photo

class PhotoService(
    private val networkRequestManager: NetworkRequestManager,
    private val client: Client,
) {
    private val perPage = 30

    suspend fun fetchPhoto(id: String): Result<Photo> {
        return networkRequestManager.apiRequest {
            client.fetchPhoto(id)
        }
    }

    suspend fun fetchPhotos(
        page: Int,
        perPage: Int = this.perPage
    ): Result<List<Photo>> {
        return networkRequestManager.apiRequest {
            client.fetchPhotos(page, perPage)
        }
    }
}
