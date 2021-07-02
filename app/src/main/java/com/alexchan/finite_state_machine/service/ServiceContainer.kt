package com.alexchan.finite_state_machine.service

import com.alexchan.finite_state_machine.api.Client
import com.alexchan.finite_state_machine.api.ClientGenerator
import com.alexchan.finite_state_machine.core.network.NetworkRequestManager

object ServiceContainer {

    private val networkRequestManager = NetworkRequestManager()
    val photoService: PhotoService

    init {
        val client = ClientGenerator.createClient(Client::class.java)
        photoService = PhotoService(
            networkRequestManager,
            client,
        )
    }
}
