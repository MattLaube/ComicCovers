package com.laubetech.comiccovers.models

import com.laubetech.comiccovers.models.data.Comic
import com.laubetech.comiccovers.models.response.MarvelResponse

class ComicData() {
    var coverLink : String = ""
    var issueTitle : String = ""
    var issueLength : Long = 0
    var coverImageName : String = ""


    constructor(newData: MarvelResponse) : this(){
        coverLink= fetchCoverLink(newData)
        issueTitle = fetchIssueTitle(newData)
        issueLength =  fetchIssueLength(newData)
        coverImageName = fetchCoverImageName(newData)

    }

    constructor(newData: Comic?): this(){
        if (newData != null) {
            coverImageName = newData.issueId
            issueTitle = newData.issueTitle
            issueTitle = newData.issueTitle
        } else{
            coverLink = "NA"
        }
    }

    private fun fetchCoverLink(newData: MarvelResponse):String{
        return newData.data.results[0].images[0].path
    }

    private fun fetchIssueTitle(newData: MarvelResponse):String{
        return newData.data.results[0].title
    }

    private fun fetchIssueLength(newData: MarvelResponse):Long{
        return newData.data.results[0].pageCount
    }

    // we store the coverImage with the id of the comic
    private fun fetchCoverImageName(newData: MarvelResponse):String{
        return newData.data.results[0].id.toString()
    }

    override fun toString(): String {
        return "$issueTitle $issueLength Pages"
    }

    fun toComic(): Comic{
        val newComic = Comic(coverImageName,issueTitle, issueLength.toString())
        return newComic
    }

}