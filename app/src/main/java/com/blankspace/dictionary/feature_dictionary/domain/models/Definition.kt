package com.blankspace.dictionary.feature_dictionary.domain.models

data class Definition(
    val antonyms: List<String>,
    val definition: String,
    val example : String?,
    val synonyms: List<Any>
){
    fun toDefinition() : Definition{
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}
