package com.cerve.co.familyfeudincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import com.cerve.co.familyfeudincompose.ui.theme.FamilyFeudInComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDecorFitsSystemWindows(window, false)

        setContent {
            FamilyFeudInComposeTheme {

            }
        }
    }
}
