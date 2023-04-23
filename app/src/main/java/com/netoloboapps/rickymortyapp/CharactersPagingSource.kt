package com.netoloboapps.rickymortyapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class CharactersPagingSource
@Inject constructor(
    private val restInterface: RickMortyApiService
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPage = params.key ?: 1
            val repos = restInterface.getCharacters(nextPage).characters
            LoadResult.Page(
                data = repos,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }
}