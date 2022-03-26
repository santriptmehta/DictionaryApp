package com.blankspace.dictionary.feature_dictionary.data.remote.dto

import com.blankspace.dictionary.feature_dictionary.domain.models.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val origin: String,
    val phonetics: List<PhoneticDto>,
    val word: String
){
    fun toWordInfo(): WordInfo{
        return WordInfo(
            meanings = meanings.map { it.toMeaning() },
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }
}