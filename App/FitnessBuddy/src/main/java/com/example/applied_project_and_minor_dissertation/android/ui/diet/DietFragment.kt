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
import com.example.applied_project_and_minor_dissertation.android.RetrofitHelper
import kotlinx.android.synthetic.main.activity_diary.*
import kotlinx.android.synthetic.main.activity_diet.*
import kotlinx.android.synthetic.main.activity_diet.view.*
import misc.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DietFragment : Fragment() {
    private lateinit var spinner: Spinner;
    private lateinit var spinner2: Spinner;
    private lateinit var type: String;
    private lateinit var type2: String;
    private lateinit var stored: MutableList<String>;
    private var visibility = visible;

    var found: Boolean = false;

    //    val listArray = arrayOf<Int>()
    var allEDs: MutableMap<TextView, EditText> = HashMap()// list of edit text
    private val food =
        arrayOf("Meat", "Vegetables", "Fruit")// will decide what second spinner will display
    val meat = arrayOf("Pork", "Chicken", "Beef", "Lamb", "Turkey", "Goat", "Duck")
    val vegetables = arrayOf(
        "Broccoli",
        "Carrots",
        "Potato",
        "Tomatoes",
        "Onions",
        "Mushroom",
        "Lettuce",
        "Pumpkin"
    )
    val fruit = arrayOf(
        "Apples",
        "Oranges",
        "Raspberries",
        "Banana",
        "Pineapple",
        "Watermelon",
        "Mangos",
        "Pears"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stored = ArrayList();
        val root = inflater.inflate(R.layout.activity_diet, container, false)
        visibility = root.findViewById(R.id.tableLayout)
        spinner = root.findViewById(R.id.spinner)
        spinner2 = root.findViewById(R.id.spinner2)
        spinner?.adapter = activity?.applicationContext?.let {
            ArrayAdapter(
                it,
                R.layout.support_simple_spinner_dropdown_item,
                food
            )
        } as SpinnerAdapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                type = parent?.getItemAtPosition(position).toString()
                changeSpinnerContent(type);
                view?.textView?.isSingleLine = false
                view?.textView?.gravity = 5
                spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        type2 = parent?.getItemAtPosition(position).toString()

                        Toast.makeText(activity, type2, Toast.LENGTH_LONG).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        println("error")
                    }
                }

                Toast.makeText(activity, type, Toast.LENGTH_LONG).show()
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
            var found: Boolean = false;
            allEDs.forEach {
                println(it.key.text)
                if (type2 == it.key.text) {
                    Log.d("type2$type2", "Selected ${it.key.text}")
                    found = true;
                }


            }
            if (!found) {
                addTable(view, type2)
                tableLayout.visibility = View.VISIBLE
                visible.visibility = View.VISIBLE
            }
            //tallyTableTV(view, type2)

        }
        addData.setOnClickListener { view ->
            val foods: MutableList<food> = ArrayList();
            var emptyInput: Boolean = false
            allEDs.forEach {
                Log.d("Test", "Vlue ${it.key.text}")
                if(!stored.contains(it.key.text.toString())){
                    foods.add(food(it.key.text.toString(), it.value.text.toString().toFloat()))
                    stored.add(it.key.text.toString())

                }

            }
            if(foods.isNotEmpty()){
                tallyTableTV(FoodDTO(foods)){}
                tableLayout.removeAllViews()
                visible.visibility = View.GONE
            }


            //tallyTableTV(FoodDTO(foods)) {}

            Log.d("foods${foods.toString()}", "FOOOOD")
        }

    }


    private fun tallyTableTV(food: FoodDTO, onResult: (List<FooDResponse>?) -> Unit) {

        val retrofit = RetrofitHelper.buildService(AuthApi::class.java)
        retrofit.sendFood(food).enqueue(
            object : Callback<List<FooDResponse>> {
                override fun onResponse(
                    call: Call<List<FooDResponse>>,
                    response: Response<List<FooDResponse>>
                ) {
                    if (response.isSuccessful) {
                        val addedUser = response.body()
                        Log.d("Error", response.body().toString())
                        onResult(addedUser)

                        val dynamic_tv: TextView
                        dynamic_tv = TextView(activity?.applicationContext);
                        dynamic_tv.textSize = 15f
                        response.body()?.forEach() {
                            //dynamic_tv.append(it.foodName + ": " + it.calories + " calories," + it.carbs + "carbs, " + +it.protein + " protein, " + it.foodWeight + " food weight in gramms, " + "\n")
                            dynamic_tv.append(String.format("%s:\n%.2f calories %.2f carbs\n%.2f protein %.2f qty", it.foodName, it.calories, it.carbs, it.protein, it.foodWeight  ))

                        }
                        tallyLayout.addView(dynamic_tv)//creates textview

                    } else {
                        Log.d("Error", response.message().toString())
                        Log.d("Error", response.code().toString())
                        Log.d("Error", response.body().toString())
                        Log.d("Error", response.errorBody().toString())
                        Log.d("Error", response.raw().toString())
                    }

                }

                override fun onFailure(call: Call<List<FooDResponse>>, t: Throwable) {
                    onResult(null)
                }

            }
        )


    }

    private fun addTable(view: View, type2: String) {
        // creating TextView programmatically
        val dynamic_tv: TextView
        val dynamic_et: EditText
        dynamic_tv = TextView(activity?.applicationContext);
        dynamic_et = EditText(activity?.applicationContext);
        Log.d("dataClicked", "Selected")
        dynamic_et.id = id
        //each editText gets set to Return the identifier this fragment is known by
        dynamic_tv.textSize = 15f
        dynamic_et.textSize = 15f
//      "This is a dynamic TextView generated programmatically in Kotlin"
        dynamic_tv.text = type2
        dynamic_et.inputType = InputType.TYPE_CLASS_PHONE
        dynamic_et.hint = "Enter food weight in grams here"
//        TYPE_TEXT_VARIATION_PASSWORD another layout for  inputtype
        allEDs[dynamic_tv] = dynamic_et;
        tableLayout.addView(dynamic_tv)//creates textview
        tableLayout.addView(dynamic_et)//creates editTextview
    }

    private fun changeSpinnerContent(type: String) {
        when (type) {
            "Meat" -> spinner2?.adapter = activity?.applicationContext?.let {
                ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    meat
                )
            } as SpinnerAdapter
            "Vegetables" -> spinner2?.adapter = activity?.applicationContext?.let {
                ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    vegetables
                )
            } as SpinnerAdapter
            "Fruit" -> spinner2?.adapter = activity?.applicationContext?.let {
                ArrayAdapter(
                    it,
                    R.layout.support_simple_spinner_dropdown_item,
                    fruit
                )
            } as SpinnerAdapter
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
    }

}
