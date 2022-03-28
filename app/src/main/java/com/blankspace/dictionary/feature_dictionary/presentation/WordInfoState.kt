package com.blankspace.dictionary.feature_dictionary.presentation

import com.blankspace.dictionary.feature_dictionary.domain.models.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
