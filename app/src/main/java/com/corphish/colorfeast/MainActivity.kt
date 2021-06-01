package com.corphish.colorfeast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.corphish.colorfeast.ui.theme.ColorFeastTheme
import com.corphish.colors.ktx.ColorUtils
import com.corphish.colors.ktx.onSurfaceColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorFeastTheme {
                CustomizeSystemUI()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RenderLetters()
                }
            }
        }
    }
}

@Composable
fun CustomizeSystemUI() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    val statusBarColor = MaterialTheme.colors.primaryVariant

    SideEffect {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun RenderLetters() {
    val list = ('A'..'Z').map { it.toString() }
    LazyVerticalGrid(cells = GridCells.Fixed(4)) {
        items(list) {
            Box(modifier = Modifier.padding(8.dp)) {
                Letter(letter = it)
            }
        }
    }
}

@Composable
fun Letter(letter: String) {
    val background = ColorUtils.getRandomColor()
    Text(
        text = letter.toUpperCase(),
        modifier = Modifier
            .background(
                color = Color(background),
                shape = MaterialTheme.shapes.medium
            )
            .fillMaxWidth(fraction = 0.9f)
            .padding(all = 16.dp),
        color = Color(background.onSurfaceColor),
        textAlign = TextAlign.Center,
        fontSize = 24.sp
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ColorFeastTheme {
        Letter("Android")
    }
}