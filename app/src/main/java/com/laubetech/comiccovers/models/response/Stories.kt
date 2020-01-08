package com.laubetech.comiccovers.models.response

data class Stories (
    val available: Long,
    val collectionURI: String,
    val items: List<StoriesItem>,
    val returned: Long
)
