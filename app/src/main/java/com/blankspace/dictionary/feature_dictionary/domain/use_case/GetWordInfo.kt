package com.blankspace.dictionary.feature_dictionary.domain.use_case

import com.blankspace.dictionary.core.util.Resource
import com.blankspace.dictionary.feature_dictionary.domain.models.WordInfo
import com.blankspace.dictionary.feature_dictionary.domain.reprository.WordInfoReprository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val reprository: WordInfoReprository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>>{
        if(word.isBlank()) {
            return flow { }
        }
        return reprository.getWordInfo(word)
    }
}