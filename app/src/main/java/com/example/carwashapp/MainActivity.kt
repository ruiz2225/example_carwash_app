package com.example.carwashapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.carwashapp.ui.theme.CarWashAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarWashAppTheme {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CarWashAppTheme {
    }
}