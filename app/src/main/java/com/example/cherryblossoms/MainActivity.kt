package com.example.cherryblossoms

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cherryblossoms.ui.theme.CherryBlossomsTheme
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CherryBlossomsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val jsonString = getJson(resources)
                    val cherryList = getCherryList(jsonString)
                    Column{
                        cherryList.forEach { cherry ->
                            Row{
                                Text(text = cherry.name)
                                Text(text = "${cherry.longitude}, ${cherry.latitude}")
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getJson(resources: Resources): String {
    val inputStream = resources.assets.open("100Cherry_List.json")
    inputStream.bufferedReader().use{ bufferedReader ->
        return bufferedReader.readText()
    }
}

fun getCherryList(str: String): List<Cherry> {
    val obj = Json.decodeFromString<List<Cherry>>(str)
    return obj
}