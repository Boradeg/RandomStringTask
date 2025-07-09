package com.app.randomstringtask.Data.mapper

import com.app.randomstringtask.Data.local.RandomStringEntity
import com.app.randomstringtask.presentations.modal.RandomStringDisplay

fun RandomStringDisplay.toEntity(): RandomStringEntity {
    return RandomStringEntity(
        value = this.value,
        length = this.length,
        created = this.created
    )
}