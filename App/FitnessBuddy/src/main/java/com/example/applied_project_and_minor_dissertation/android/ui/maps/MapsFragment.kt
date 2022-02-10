package com.example.applied_project_and_minor_dissertation.android.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.applied_project_and_minor_dissertation.android.databinding.ActivityMapsBinding

class MapsFragment : Fragment() {

    private var _binding: ActivityMapsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(MapsViewModel::class.java)

        _binding = ActivityMapsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMaps
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}