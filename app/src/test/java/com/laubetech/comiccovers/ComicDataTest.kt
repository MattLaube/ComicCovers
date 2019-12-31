package com.laubetech.comiccovers

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.laubetech.comiccovers.models.ComicData
import com.laubetech.comiccovers.models.response.MarvelResponse
import junit.framework.Assert.assertTrue
import org.junit.Test

class ComicDataTest {

    var expectedOutPut = "Immortal Hulk (2018) #19 32 Pages"

    @Test
    fun testComicData(){
        //build out some test data
        val gson = Gson()
        val marvelData: MarvelResponse = gson.fromJson(testJSON)
        val testComicData = ComicData(marvelData)

        val testOutput = testComicData.toString()
        assertTrue(testOutput.contentEquals(expectedOutPut) )
    }

    val testJSON = "{\n" +
            "  \"data\": {\n" +
            "    \"count\": 1,\n" +
            "    \"limit\": 20,\n" +
            "    \"offset\": 0,\n" +
            "    \"results\": [\n" +
            "      {\n" +
            "        \"characters\": {\n" +
            "          \"available\": 1,\n" +
            "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/73823/characters\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"name\": \"Hulk\",\n" +
            "              \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1009351\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"returned\": 1\n" +
            "        },\n" +
            "        \"description\": null,\n" +
            "        \"diamondCode\": \"APR190792\",\n" +
            "        \"digitalId\": 51722,\n" +
            "        \"ean\": \"\",\n" +
            "        \"events\": {\n" +
            "          \"available\": 0,\n" +
            "          \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/73823/events\",\n" +
            "          \"items\": [],\n" +
            "          \"returned\": 0\n" +
            "        },\n" +
            "        \"format\": \"Comic\",\n" +
            "        \"id\": 73823,\n" +
            "        \"images\": [\n" +
            "          {\n" +
            "            \"extension\": \"jpg\",\n" +
            "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/f/50/5cf7db2626cdb\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"extension\": \"jpg\",\n" +
            "            \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/0/04/5c9bb9ee8c52e\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"isbn\": \"\",\n" +
            "        \"issn\": \"1941-2142\",\n" +
            "        \"issueNumber\": 19,\n" +
            "        \"modified\": \"2019-06-12T09:03:45-0400\",\n" +
            "        \"pageCount\": 32,\n" +
            "        \"thumbnail\": {\n" +
            "          \"extension\": \"jpg\",\n" +
            "          \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/f/50/5cf7db2626cdb\"\n" +
            "        },\n" +
            "        \"title\": \"Immortal Hulk (2018) #19\",\n" +
            "        \"variantDescription\": \"\",\n" +
            "        \"variants\": []\n" +
            "      }\n" +
            "    ],\n" +
            "    \"total\": 1\n" +
            "  },\n" +
            "  \"etag\": \"55da33bc01091670617416186a182d052e54f122\",\n" +
            "  \"status\": \"Ok\"\n" +
            "}"
}