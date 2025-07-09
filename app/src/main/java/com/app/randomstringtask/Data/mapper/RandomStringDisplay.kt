package com.app.randomstringtask.Data.mapper

import com.app.randomstringtask.Data.local.RandomStringEntity
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import com.app.randomstringtask.presentations.modal.RandomStringDisplayFetch


fun RandomStringDisplay.toEntity() = RandomStringEntity(
    id = id,
    value = value,
    length = length,
    created = created
)


fun RandomStringDisplayFetch.toEntity(): RandomStringEntity {
    return RandomStringEntity(
        value = value,
        length = length,
        created = created
    )
}