package com.example.quoteapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.quoteapp.presentation.ErrorScreen
import com.example.quoteapp.presentation.LoadingScreen
import com.example.quoteapp.presentation.MainViewModel
import com.example.quoteapp.presentation.RandomQuoteScreen
import com.example.quoteapp.ui.theme.QuoteAppTheme
import com.example.quoteapp.util.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val apiState : ApiState = mainViewModel.response.value
                    val scope : CoroutineScope = rememberCoroutineScope()
                    val modifier = Modifier
                    LaunchedEffect(key1 = true,
                        block = { mainViewModel.getRandomQuote()})
                    Scaffold(
                        modifier = modifier,
                        topBar = {
                            TopAppBar(title = {
                                Text(text = "Quote",
                                    fontFamily = FontFamily.Serif)
                            })
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = {
                                scope.launch {
                                    mainViewModel.getRandomQuote()
                                }
                            }) {
                                Icon(imageVector = Icons.Rounded.Shuffle,
                                    contentDescription = null )
                            }
                        }
                        
                    ) {
                             when(apiState){
                                 is ApiState.Success ->{
                                     val quote = apiState.data.body()!!
                                     RandomQuoteScreen(quote = quote)
                                 }
                                 is ApiState.Loading ->{
                                     LoadingScreen(modifier = modifier)
                                 }
                                 is ApiState.Failure ->{
                                     ErrorScreen(error = apiState.error.message ?: "Something went wrong")
                                 }
                                 else -> Unit
                             }
                    }
                }
            }
        }
    }
}
