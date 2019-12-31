package com.laubetech.comiccovers.models.response

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