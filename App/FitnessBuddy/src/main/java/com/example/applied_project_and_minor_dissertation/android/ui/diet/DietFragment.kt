package com.example.applied_project_and_minor_dissertation.android.ui.diet

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.applied_project_and_minor_dissertation.android.R
import kotlinx.android.synthetic.main.activity_diet.*

class DietFragment : Fragment() {
    private lateinit var spinner:Spinner;
    private lateinit var spinner2:Spinner;
    private lateinit var type: String;
    private lateinit var type2: String;
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
                type  = parent?.getItemAtPosition(position).toString()
                changeSpinnerContent(type);
                spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        type2 = parent?.getItemAtPosition(position).toString()

                        Toast.makeText(activity,type2, Toast.LENGTH_LONG).show()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener { view ->
            addTable(view, type2)
            Log.d("tableClicked", "Selected")
        }
        addData.setOnClickListener { view ->
            //sendTable()
            tallyTable(view, type2)
            Log.d("dataClicked", "Selected")
        }


    }

    private fun tallyTable(view: View, type2: String) {
        // creating TextView programmatically
        val dynamic_tv: TextView

        dynamic_tv = TextView(activity?.applicationContext);
        dynamic_tv.textSize = 15f
//      "This is a dynamic TextView generated programmatically in Kotlin"
        dynamic_tv.text = type2

        tallyLayout.addView(dynamic_tv)
    }

    private fun sendTable() {
        TODO("Not yet implemented")
    }

    private fun addTable(view: View, type2: String) {
        // creating TextView programmatically
        val dynamic_tv: TextView
        val dynamic_et: EditText

        dynamic_tv = TextView(activity?.applicationContext);
        dynamic_et = EditText(activity?.applicationContext);

        dynamic_tv.textSize = 15f
        dynamic_et.textSize = 15f
//      "This is a dynamic TextView generated programmatically in Kotlin"
        dynamic_tv.text = type2
        dynamic_et.inputType = InputType.TYPE_CLASS_PHONE
//        TYPE_TEXT_VARIATION_PASSWORD

        tableLayout.addView(dynamic_tv)
        tableLayout.addView(dynamic_et)

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