package com.example.simplemvvm.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.simplemvvm.api.ApiService
import com.example.simplemvvm.models.ResultCharacter

class CharacterPagingSource(private val apiService: ApiService) :
    PagingSource<Int, ResultCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, ResultCharacter>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultCharacter> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAllCharacters(currentPage)
            val data = response.body()?.results ?: emptyList()
            val responseData = mutableListOf<ResultCharacter>()
            responseData.addAll(data)

            Log.e("CharacterPagingSource", "load: $data")

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )


        } catch (e: Exception) {
            Log.e("CharacterPagingSource", "load: error try")
            Log.e("CharacterPagingSource", "load: $e")
            LoadResult.Error(e)
        }
    }


}