package com.example.cherryblossoms

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cherryblossoms.data.ApiResult
import com.example.cherryblossoms.data.ApiSearch
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import com.github.kittinunf.result.Result

class DataViewModel : ViewModel() {

    val cherryList = mutableStateListOf<ApiSearch>()

    fun query() {
        viewModelScope.launch(Dispatchers.IO) {
            val url =
                "https://ja.wikipedia.org/w/api.php" +
                        "?action=query" +
                        "&format=json" +
                        "&prop=images" +
                        "&list=search" +
                        "&formatversion=2" +
                        "&imlimit=1" +
                        "&srsearch=桜の名所" +
                        "&srlimit=500"

            val (_, _, result) = Fuel
                .get(url)
                .responseString()

            when (result) {
                is Result.Success -> {
                    val json = Json {
                        ignoreUnknownKeys = true
                    }

                    val apiResult =
                        json.decodeFromString<ApiResult>(result.value)

                    withContext(Dispatchers.Main) {
                        cherryList.clear()
                        cherryList.addAll(apiResult.query.search)
                    }
                }

                is Result.Failure -> {
                    result.error.printStackTrace()
                }
            }
        }
    }
}