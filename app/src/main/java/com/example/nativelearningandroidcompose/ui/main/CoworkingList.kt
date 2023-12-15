package com.example.nativelearningandroidcompose.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nativelearningandroidcompose.data.CoworkingSpace

@Composable
fun CoworkingList(viewModel: MainViewModel, onItemClicked: (CoworkingSpace) -> Unit) {
    val coworkingSpaces by viewModel.responseData.collectAsStateWithLifecycle()
    LazyColumn {
        items(items = coworkingSpaces, itemContent = { coworkingSpace ->
            Column (Modifier.clickable { onItemClicked(coworkingSpace) }) {
                Text(text = coworkingSpace.organisation!!,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp, 10.dp)
                )

                Text(text = coworkingSpace.address!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Divider(modifier = Modifier.fillMaxWidth().height(1.dp))
            }
        })
    }
    DisposableEffect(Unit) {
        viewModel.getCoworkingSpaces()
        onDispose {}
    }
}