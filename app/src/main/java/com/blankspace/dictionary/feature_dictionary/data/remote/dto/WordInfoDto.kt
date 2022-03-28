package com.blankspace.dictionary.feature_dictionary.data.remote.dto

import com.blankspace.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val origin: String,
    val phonetics: List<PhoneticDto>,
    val word: String
){
    fun toWordInfoEntity(): WordInfoEntity{
        return WordInfoEntity(
            meaning = meanings.map { it.toMeaning() },
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }
}