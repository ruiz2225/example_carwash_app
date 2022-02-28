package com.example.carwashapp.view.services

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun ServiceList() {
    Box(modifier = Modifier.fillMaxSize())
    {
        LazyColumn {

            item {
                ServiceListItem()
                Divider()
            }

            item{
                ServiceListItem()
                Divider()
            }

            item {
                ServiceListItem()
                Divider()
            }
        }
    }

}
