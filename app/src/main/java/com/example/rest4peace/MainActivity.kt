package com.example.rest4peace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.rest4peace.ui.startScreen
import com.example.rest4peace.ui.theme.Rest4PeaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("lifecycle methods","inside oncreate method")
        super.onCreate(savedInstanceState)
        setContent {
            Rest4PeaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(255,255,255,255)//white color
                ) {//MaterialTheme.colorScheme.background//changed app background to white color
                    //Greeting("Android")
                    startScreen()
                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("lifecycle methods", "Inside on restart")
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Rest4PeaceTheme {
        //Greeting("Android")
        startScreen()
    }
}