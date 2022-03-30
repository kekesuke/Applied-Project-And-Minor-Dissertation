package com.example.applied_project_and_minor_dissertation.android.ui.`step-counter`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.applied_project_and_minor_dissertation.android.R
import com.example.applied_project_and_minor_dissertation.android.databinding.ActivityDietBinding

class DietFragment : Fragment() {

    private var _binding: ActivityDietBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_diet, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}