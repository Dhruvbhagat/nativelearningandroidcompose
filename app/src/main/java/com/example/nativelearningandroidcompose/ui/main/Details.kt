package com.example.nativelearningandroidcompose.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nativelearningandroidcompose.data.CoworkingSpace

@Composable
fun DetailScreen(coworkingSpace: CoworkingSpace) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 0.dp)
    ) {
        Text(text = "Organisation: ${coworkingSpace.organisation}", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Address: ${coworkingSpace.address}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Website: ${coworkingSpace.website}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Latitude: ${coworkingSpace.latitude}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Longitude: ${coworkingSpace.longitude}", style = MaterialTheme.typography.bodyMedium)
    }
}