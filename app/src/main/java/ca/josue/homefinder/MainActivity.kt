package ca.josue.homefinder

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.rememberNavController
import ca.josue.homefinder.navigation.SetupNavGraph
import ca.josue.homefinder.ui.theme.HomeFinderTheme
import ca.josue.homefinder.utils.buildExoPlayer
import ca.josue.homefinder.utils.buildPlayerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize = calculateWindowSizeClass(activity = this)
            val navController = rememberNavController()
            val exoplayer = remember { this.buildExoPlayer(getVideoUri()) }

            HomeFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DisposableEffect(
                        AndroidView(
                            factory = { it.buildPlayerView(exoplayer) },
                            modifier = Modifier.fillMaxSize()
                        )
                    ) {
                        onDispose {
                            exoplayer.release()
                        }
                    }

                    SetupNavGraph(
                        navController = navController,
                        windowSize = windowSize.widthSizeClass
                    )
                }
            }
        }
    }

    private fun getVideoUri(): Uri {
        val videoUri = "android.resource://$packageName/${R.raw.houses}"
        return Uri.parse(videoUri)
    }
}