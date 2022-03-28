package com.blankspace.dictionary.feature_dictionary.data.reprository

import com.blankspace.dictionary.core.util.Resource
import com.blankspace.dictionary.feature_dictionary.data.local.WordInfoDao
import com.blankspace.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.blankspace.dictionary.feature_dictionary.domain.models.WordInfo
import com.blankspace.dictionary.feature_dictionary.domain.reprository.WordInfoReprository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoReprositoryImpl(
    private val api : DictionaryApi,
    private val dao : WordInfoDao
): WordInfoReprository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map {it.toWordInfo()}
        emit(Resource.Loading(data = wordInfos))

        try{
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map{it.toWordInfoEntity()})
        }catch (e: HttpException){

        }catch (e: IOException){

        }
    }
}