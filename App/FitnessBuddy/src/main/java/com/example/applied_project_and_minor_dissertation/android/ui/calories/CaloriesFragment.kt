package com.example.applied_project_and_minor_dissertation.android.ui.calories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.applied_project_and_minor_dissertation.android.databinding.ActivityCaloriesBinding

class CaloriesFragment : Fragment() {

    private var _binding: ActivityCaloriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[CaloriesViewModel::class.java]

        _binding = ActivityCaloriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCalories
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}