package com.blankspace.dictionary.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankspace.dictionary.core.util.Resource
import com.blankspace.dictionary.feature_dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val GetWordInfo: GetWordInfo
) : ViewModel(){

    private val _searchQuery = mutableStateOf("")
    val searchQuery : State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _evenFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _evenFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String){
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            GetWordInfo(query)
                .onEach { result ->
                    when(result){
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data?: emptyList(),
                                isLoading = false
                            )
                            _evenFlow.emit(UIEvent.ShowSnackbar(
                                result.message?:"Unknown Error"
                            ))

                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }

    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
    }
}