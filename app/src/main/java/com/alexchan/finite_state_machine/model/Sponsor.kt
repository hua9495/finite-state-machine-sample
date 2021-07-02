package com.alexchan.finite_state_machine.model

import java.util.UUID

data class Sponsor(
    var id: String = UUID.randomUUID().toString()
)
