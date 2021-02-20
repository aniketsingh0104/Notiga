package com.dullabs.notiga.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.dullabs.notiga.R

class BottomAppBarFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bottomNavigationItems = listOf(
            com.dullabs.notiga.ui.BottomNavigationScreens.Inbox,
            com.dullabs.notiga.ui.BottomNavigationScreens.BatchTime,
            com.dullabs.notiga.ui.BottomNavigationScreens.Configure
        )
        val view  = inflater.inflate(R.layout.bottom_bar, container, false)
        val bottomAppBar  = view.findViewById<ViewGroup>(R.id.bottomAppBar)
        val bottomNavBarComposeView = ComposeView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

            setContent {

            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}