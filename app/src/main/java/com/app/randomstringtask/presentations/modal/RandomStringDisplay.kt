package com.app.randomstringtask.presentations.modal

data class RandomStringDisplayFetch(
    val value: String,
    val length: Int,
    val created: String
)


data class RandomStringDisplay(
    val id: Int,
    val value: String,
    val length: Int,
    val created: String
)
