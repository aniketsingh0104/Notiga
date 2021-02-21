package com.dullabs.notiga.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dullabs.notiga.MainActivity
import com.dullabs.notiga.databinding.FragmentTermsBinding

class TermsFragment : Fragment() {

    private var _binding: FragmentTermsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTermsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        (activity as MainActivity).hideBottomBar()
        (activity as MainActivity).hideFab()
        super.onAttach(context)
    }

    override fun onDetach() {
        (activity as MainActivity).showBottomBar()
        (activity as MainActivity).showFab()
        super.onDetach()
    }
}