package com.example.carwashapp.view

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carwashapp.R
import com.example.carwashapp.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController){
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.4f,
            animationSpec = tween(
                durationMillis = 300,
                easing = {
                    OvershootInterpolator(3f).getInterpolation(it)
                }
            ))
        delay(2000L)
        navController.popBackStack()
        navController.navigate(AppScreens.HomeScreen.route)
    }
    Splash(scale = scale.value)
}

@Composable
fun Splash(scale: Float){
    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Image(modifier = Modifier
            .size(388.dp)
            .scale(scale = scale), painter = painterResource(id = R.mipmap.icon_splash), contentDescription = "Icon Splash")
    }
}

@Composable
@Preview(showSystemUi = true)
fun SplashScreenPreview(){
    Splash(scale = 0.5f)
}
