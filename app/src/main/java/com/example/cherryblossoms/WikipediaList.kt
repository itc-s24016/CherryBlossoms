package com.example.cherryblossoms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.cherryblossoms.data.ApiSearch

@Composable
fun WikipediaList(list: List<ApiSearch>?, onSelected: (ApiSearch) -> Unit){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        list?.let {
            items(list.size){ index ->
                WikipediaRow(search = list[index], onSelected = onSelected)
            }
        }
    }
}