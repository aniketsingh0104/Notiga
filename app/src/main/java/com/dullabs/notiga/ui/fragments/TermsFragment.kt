package com.dullabs.notiga.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dullabs.notiga.R

class TermsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        (activity as MainActivity).setupNavigationIcon()
        return inflater.inflate(R.layout.fragment_apps_configure, container, false)
    }
}