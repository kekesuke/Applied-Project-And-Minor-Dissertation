package com.example.applied_project_and_minor_dissertation.android.ui.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.applied_project_and_minor_dissertation.android.R
import kotlinx.android.synthetic.main.activity_diary.*
import kotlin.math.pow


class DiaryFragment : Fragment() {

    private var imageMale = image_male
    private var imageGirl = image_female_blur
    private var weight = weight_value
    private var height = height_value
    private var calculateButton = calculate_button
    private var BMI = bmi
    private var bmiStatus = bmi_status
    private var BMIView = bmiView
    private var calculateAgainButton = reCalculate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        val root = inflater.inflate(R.layout.activity_diary, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        imageMale = root.findViewById(R.id.image_male)
        imageGirl = root.findViewById(R.id.image_female_blur)
        weight = root.findViewById(R.id.weight_value)
        height = root.findViewById(R.id.height_value)
        calculateButton = root.findViewById(R.id.calculate_button)
        BMI = root.findViewById(R.id.bmi)
        bmiStatus = root.findViewById(R.id.bmi_status)
        BMIView = root.findViewById(R.id.bmiView)
        calculateAgainButton = root.findViewById(R.id.reCalculate)

        imageMale.setOnClickListener{
            imageMale.setImageResource(R.drawable.ic_male)
            imageGirl.setImageResource(R.drawable.ic_female_blur)
        }
        imageGirl.setOnClickListener {
            imageMale.setImageResource(R.drawable.ic_male_faded)
            imageGirl.setImageResource(R.drawable.ic_female)
        }

        calculateButton.setOnClickListener {
            var weightValue = 0.0
            var heightValue = 0.0
            if (weight.text.toString().isNotEmpty()) {
                weightValue = weight.text.toString().toDouble()
            }
            if (height.text.toString().isNotEmpty()) {
                heightValue = (height.text.toString().toDouble() / 100)
            }
            if (weightValue > 0.0 && heightValue > 0.0) {
                val bmiValue = String.format("%.2f", weightValue / heightValue.pow(2))
                bmi.text = bmiValue
                bmiStatus.text = bmiStatusValue(weightValue / heightValue.pow(2))
                bmiView.visibility = VISIBLE
                calculateButton.visibility = GONE
            } else
                Toast.makeText(activity, "Please Input valid Weight and Height Values greater than 0", Toast.LENGTH_LONG).show()

        }
        calculateAgainButton.setOnClickListener {
            bmiView.visibility = GONE
            calculateButton.visibility = VISIBLE
            weight.text.clear()
            height.text.clear()
            weight.requestFocus()
        }

        return root
    }
    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if (bmi < 18.5)
            bmiStatus = "Underweight"
        else if (bmi >= 18.5 && bmi < 25)
            bmiStatus = "Normal"
        else if (bmi >= 25 && bmi < 30)
            bmiStatus = "Overweight"
        else if (bmi > 30)
            bmiStatus = "Obese"
        return bmiStatus
    }
}