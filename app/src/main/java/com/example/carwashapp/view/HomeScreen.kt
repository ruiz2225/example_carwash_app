package com.example.carwashapp.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carwashapp.R

@Composable
fun HomeScreen(){
    Scaffold() {
        ContentHomeScreen()
    }
}

@Composable
fun ContentHomeScreen(){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        CardList(title = stringResource(id = R.string.title_card_services), subtitle = stringResource(id = R.string.subtitle_card_services))
        CardClients(title = stringResource(id = R.string.title_card_clientsvip), subtitle = stringResource(id = R.string.subtitle_card_clientsvip))
        CardAdmin(title = stringResource(id = R.string.title_card_admin), subtitle = stringResource(id = R.string.subtitle_card_admin))
    }
}

@Composable
fun TitleCard(textTitle : String){
    Text(text = textTitle,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier
            .padding(top = 15.dp, start = 16.dp),
        textAlign = TextAlign.Start)
}

@Composable
fun SubTitleCard(textSubTitle : String){
    Text(text = textSubTitle,
        style = MaterialTheme.typography.subtitle2,
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier
            .padding(start = 16.dp, bottom = 18.dp)
            .alpha(0.5f),
        textAlign = TextAlign.Start)

}

@Composable
fun ImageCardList(){
    Image(painter = painterResource(id = R.mipmap.carlist),
        contentDescription = stringResource(id = R.string.desc_image_cards),
        contentScale = ContentScale.Crop)
}

@Composable
fun ImageCardClients(){
    Image(painter = painterResource(id = R.mipmap.client_vip),
        contentDescription = stringResource(id = R.string.desc_image_cards),
        contentScale = ContentScale.Crop)
}

@Composable
fun ImageCardAdmin(){
    Image(painter = painterResource(id = R.mipmap.admin),
        contentDescription = stringResource(id = R.string.desc_image_cards),
        contentScale = ContentScale.Crop)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardList(title:String, subtitle:String){
    val configuration = LocalConfiguration.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp)
            .clickable { },
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        onClick = {

        }
    ) {
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Row(modifier = Modifier.fillMaxWidth()) {
                    ImageCardList()
                    Column() {
                        TitleCard(title)
                        SubTitleCard(subtitle)
                    }
                }
            }
            else -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    ImageCardList()
                    TitleCard(title)
                    SubTitleCard(subtitle)
                }
            }
        }


    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardClients(title:String, subtitle:String){
    val configuration = LocalConfiguration.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp)
            .clickable { },
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        onClick = {

        }
    ) {
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Row(modifier = Modifier.fillMaxWidth()) {
                    ImageCardClients()
                    Column() {
                        TitleCard(title)
                        SubTitleCard(subtitle)
                    }
                }
            }
            else -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    ImageCardClients()
                    TitleCard(title)
                    SubTitleCard(subtitle)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardAdmin(title:String, subtitle:String){
    val configuration = LocalConfiguration.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp)
            .clickable { },
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        onClick = {
        }
    ) {

        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Row(modifier = Modifier.fillMaxWidth()) {
                    ImageCardAdmin()
                    Column() {
                        TitleCard(title)
                        SubTitleCard(subtitle)
                    }
                }
            }
            else -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    ImageCardAdmin()
                    TitleCard(title)
                    SubTitleCard(subtitle)
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}