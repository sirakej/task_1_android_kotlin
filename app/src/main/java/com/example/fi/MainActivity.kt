package com.example.fi

import android.graphics.Paint.Align
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainContent()

            }
        }
    }

@Composable
fun mainContent(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile",
                        color = Color.White
                    )
                        },
                backgroundColor = Color.Blue
            )},
        content = {
      bodyContent()
        }
    )
}

@Composable
fun bodyContent(){
    var showWebView by remember {mutableStateOf(false) }
    if(!showWebView){
        Box{
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .height(350.dp)
                ){
                    Surface(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth(),
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "profile",
                            contentScale = ContentScale.FillBounds
                        )
                        Surface(
                            color = Color.Black.copy(alpha = 0.5f),
                        ){}

                    }
                    Surface(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                            .align(alignment = Alignment.BottomCenter),
                        shape = CircleShape,
                        border = BorderStroke(width = 4.dp, color = Color.White)

                    ) {
                        Image(modifier = Modifier
                            .width(10.dp)
                            .height(10.dp),painter = painterResource(id = R.drawable.profile), contentDescription = "profile")
                    }
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopCenter),
                        text = "Profile",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight(300)
                    )
                }
                Text(
                    text = "@Sirakej",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = {
                        showWebView = !showWebView
                    },
                    //  contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                ) {
                    Image(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        painter = painterResource(id = R.drawable.git),
                        contentDescription = "git",
                        colorFilter = ColorFilter.lighting(add = Color.White, multiply = Color.White)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Open Git")
                }
            }
        }
    }else{
        loadWebUrl()
    }

}

@Composable
fun loadWebUrl() {
    // Declare a string that contains a url
    val mUrl = "https://www.geeksforgeeks.org"

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    mainContent()
}