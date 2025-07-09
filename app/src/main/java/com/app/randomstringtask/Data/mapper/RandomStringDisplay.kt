package com.app.randomstringtask.Data.mapper

import com.app.randomstringtask.Data.local.RandomStringEntity
import com.app.randomstringtask.presentations.modal.RandomStringDisplay


fun RandomStringDisplay.toEntity() = RandomStringEntity(
    id = id,
    value = value,
    length = length,
    created = created
)


fun RandomStringForData.toEntity(): RandomStringEntity {
    return RandomStringEntity(
        value = value,
        length = length,
        created = created
    )
}