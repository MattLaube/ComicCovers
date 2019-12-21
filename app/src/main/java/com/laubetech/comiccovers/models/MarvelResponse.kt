package com.laubetech.comiccovers.models

data class MarvelResponse (
    val attributionHTML: String,
    val attributionText: String,
    val code: Long,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
)

data class Data (
    val count: Long,
    val limit: Long,
    val offset: Long,
    val results: List<Result>,
    val total: Long
)

data class Result (
    val characters: Characters,
    val collectedIssues: List<Any?>,
    val collections: List<Any?>,
    val creators: Creators,
    val dates: List<Date>,
    val description: Any? = null,
    val diamondCode: String,
    val digitalID: Long,
    val ean: String,
    val events: Characters,
    val format: String,
    val id: Long,
    val images: List<Thumbnail>,
    val isbn: String,
    val issn: String,
    val issueNumber: Long,
    val modified: String,
    val pageCount: Long,
    val prices: List<Price>,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val textObjects: List<Any?>,
    val thumbnail: Thumbnail,
    val title: String,
    val upc: String,
    val urls: List<URL>,
    val variantDescription: String,
    val variants: List<Any?>
)

data class Characters (
    val available: Long,
    val collectionURI: String,
    val items: List<Series>,
    val returned: Long
)

data class Series (
    val name: String,
    val resourceURI: String
)

data class Creators (
    val available: Long,
    val collectionURI: String,
    val items: List<CreatorsItem>,
    val returned: Long
)

data class CreatorsItem (
    val name: String,
    val resourceURI: String,
    val role: String
)

data class Date (
    val date: String,
    val type: String
)

data class Thumbnail (
    val extension: String,
    val path: String
)

data class Price (
    val price: Double,
    val type: String
)

data class Stories (
    val available: Long,
    val collectionURI: String,
    val items: List<StoriesItem>,
    val returned: Long
)

data class StoriesItem (
    val name: String,
    val resourceURI: String,
    val type: String
)

data class URL (
    val type: String,
    val url: String
)
