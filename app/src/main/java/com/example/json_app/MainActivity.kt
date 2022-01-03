package com.example.json_app

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.lang.Exception

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var tvDate: TextView
    private lateinit var userInput: EditText
    private lateinit var spinner: Spinner
    private lateinit var btnConvert: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDate = findViewById(R.id.tvDate)
        userInput = findViewById(R.id.etNumber)
        tvResult = findViewById(R.id.tvResult)

        btnConvert = findViewById(R.id.btnConvert)
        btnConvert.setOnClickListener{ convert() }


        spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this // Fetched from https://developer.android.com/guide/topics/ui/controls/spinner

        ArrayAdapter.createFromResource( // Fetched from https://developer.android.com/guide/topics/ui/controls/spinner
            this,
            R.array.currency,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    private fun convert() {
        val currentCurrency = spinner.selectedItem.toString()

        val apiInterface = CurrencyAPIClient().getClient()?.create(CurrencyAPIInterface::class.java)
        apiInterface?.getCurrency()?.enqueue(object: Callback<Currency> {
            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                try {
//                    val responseBody = response.body()!!
//                    val date = responseBody.date
//                    val euroValue = responseBody.currencyItems
//                    Log.d("MAIN", "onResponse: I am a response ${date}")
//                    Log.d("MAIN", "onResponse: I am a response ${euroValue.usd}")
//
//                    tvDate.text = "Date: $date"

//                    when (currentCurrency) {
//                        "USD" -> tvResult.text = "result: ${euroValue.usd.toString()}"
//                        "SAR" -> tvResult.text = "result: ${euroValue.sar.toString()}"
//                        "INR" -> tvResult.text = "result: ${euroValue.inr.toString()}"
//                        "AUD" -> tvResult.text = "result: ${euroValue.aud.toString()}"
//                        "JPY" -> tvResult.text = "result: ${euroValue.jpy.toString()}"
//                    }
                } catch (e: Exception) {
                    Log.d("MAIN01", "ISSUE: $e")
                }
            }

            override fun onFailure(call: Call<Currency>, t: Throwable) {
                Log.d("MAIN02", "Unable to get data")
            }
        })
    }

    // Fetched from https://developer.android.com/guide/topics/ui/controls/spinner
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}