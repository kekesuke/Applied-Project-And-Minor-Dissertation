package com.example.applied_project_and_minor_dissertation.android.ui.`step-counter`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.applied_project_and_minor_dissertation.android.R

class DietFragment : Fragment() {
    private lateinit var spinner:Spinner;
    private lateinit var spinner2:Spinner;
    private val food = arrayOf("Meat", "Vegetables", "Fruit")// will decide what second spinner will display
    val meat = arrayOf("Pork", "Chicken", "Beef","Lamb", "Turkey")
    val vegetables = arrayOf("Broccoli", "Carrots", "Potato","Tomatoes", "Onions", "Mushroom", "Lettuce", "Pumpkin")
    val fruit = arrayOf("Apples", "Oranges", "Raspberries","Banana", "Pineapple")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root=inflater.inflate(R.layout.activity_diet, container, false)
        spinner = root.findViewById(R.id.spinner)
        spinner2 = root.findViewById(R.id.spinner2)
        spinner?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, food) } as SpinnerAdapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
                changeSpinnerContent(type);
                spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val type = parent?.getItemAtPosition(position).toString()

                        Toast.makeText(activity,type, Toast.LENGTH_LONG).show()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        println("error")
                    }
                }

                Toast.makeText(activity,type, Toast.LENGTH_LONG).show()
                println(type)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("error")
            }
        }
        return root
    }

    private fun changeSpinnerContent(type:String)
    {
        when (type) {
            "Meat" -> spinner2?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, meat) } as SpinnerAdapter
            "Vegetables" -> spinner2?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, vegetables) } as SpinnerAdapter
            "Fruit" -> spinner2?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, fruit) } as SpinnerAdapter
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }

        }

    }
}