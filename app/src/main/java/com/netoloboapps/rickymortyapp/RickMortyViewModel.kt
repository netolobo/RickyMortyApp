package com.netoloboapps.rickymortyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel
@Inject constructor(
    private val charactersPagingSource: CharactersPagingSource
) : ViewModel() {
    val characters: Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            charactersPagingSource
        }
    ).flow.cachedIn(viewModelScope)
}