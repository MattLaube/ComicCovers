package com.laubetech.comiccovers.models

import com.laubetech.comiccovers.models.response.MarvelResponse

class ComicData(newData: MarvelResponse) {
    var coverLink : String = ""
    var issueTitle : String = ""
    var issueLength : Long = 0
    var coverImageName : String = ""
    init{
        coverLink= fetchCoverLink(newData)
        issueTitle = fetchIssueTitle(newData)
        issueLength =  fetchIssueLength(newData)
        coverImageName = fetchCoverImageName(newData)
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

}