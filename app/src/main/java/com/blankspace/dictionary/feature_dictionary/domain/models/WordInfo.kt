package com.blankspace.dictionary.feature_dictionary.domain.models


data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val origin: String,
    val word: String
)
