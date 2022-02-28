package com.example.carwashapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.carwashapp.ui.theme.CarWashAppTheme
import com.example.carwashapp.view.HomeScreen
import com.example.carwashapp.view.services.ServiceFormScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarWashAppTheme {
                // A surface container using the 'background' color from the theme
                ServiceFormScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CarWashAppTheme {
        Greeting("Android")
    }
}