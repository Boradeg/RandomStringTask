package com.app.randomstringtask.utils

import android.net.Uri


object Constants {
    private const val CONTENT_PROVIDER_AUTHORITY = "com.iav.contestdataprovider"
    const val CONTENT_PROVIDER_PATH = "text"
    val CONTENT_URI = Uri.parse("content://$CONTENT_PROVIDER_AUTHORITY/$CONTENT_PROVIDER_PATH")
}
