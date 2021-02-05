package com.dullabs.notiga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import com.dullabs.notiga.ui.components.NotificationComponent
import com.dullabs.notiga.ui.theme.NotigaTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotigaTheme {
                Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background) {
                    NotificationComponent()
                }
            }
        }
    }
}