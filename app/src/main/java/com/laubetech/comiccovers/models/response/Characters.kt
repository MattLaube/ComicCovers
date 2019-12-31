package com.laubetech.comiccovers.models.response

data class Characters (
    val available: Long,
    val collectionURI: String,
    val items: List<Series>,
    val returned: Long
)