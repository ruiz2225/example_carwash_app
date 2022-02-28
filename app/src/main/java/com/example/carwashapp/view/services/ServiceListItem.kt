package com.example.carwashapp.view.services

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carwashapp.R
import com.example.carwashapp.ui.theme.YellowPlate

@Composable
fun ServiceListItem(
){

    Card(elevation = 0.dp, modifier = Modifier
        .fillMaxWidth()
        .height(72.dp)) {
        Row(modifier = Modifier
            .fillMaxSize()
            .clickable { }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(100.dp)
                    .height(56.dp)
                    .background(YellowPlate)) {
                Text(
                    text = "ABC321",
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .weight(6f)) {
                Text(text = "Pepito Pérez", style = MaterialTheme.typography.subtitle1)
                Text(text = "Premium", style = MaterialTheme.typography.caption)
            }
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = stringResource(id = R.string.desc_icon_cards), modifier = Modifier
                .weight(1f)
                .padding(top = 24.dp, end = 16.dp))
        }

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewServiceListItem(){
    ServiceListItem()
}