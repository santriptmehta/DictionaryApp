package com.blankspace.dictionary.feature_dictionary.domain.reprository

import com.blankspace.dictionary.core.util.Resource
import com.blankspace.dictionary.feature_dictionary.domain.models.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoReprository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}