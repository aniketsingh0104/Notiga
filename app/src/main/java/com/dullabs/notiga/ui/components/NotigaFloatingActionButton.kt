package com.dullabs.notiga.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.dullabs.notiga.R

@Composable
fun NotigaFloatingActionButton() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(painter = painterResource(id = R.drawable.ic_pause), contentDescription = "pause batching")
    }
}