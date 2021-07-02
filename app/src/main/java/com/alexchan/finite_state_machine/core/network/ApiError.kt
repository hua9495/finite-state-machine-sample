package com.alexchan.finite_state_machine.core.network

data class ApiError(
    val message: String,
    val code: String,
    val errors: List<FieldInfo>
)

data class FieldInfo(
    val field: String?,
    val code: String?,
    val resource: String?
)
