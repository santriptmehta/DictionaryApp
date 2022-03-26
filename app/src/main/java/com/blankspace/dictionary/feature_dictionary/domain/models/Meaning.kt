package com.blankspace.dictionary.feature_dictionary.domain.models

data class Meaning(
    val definition: List<Definition>,
    val partOfSpeech: String
)
