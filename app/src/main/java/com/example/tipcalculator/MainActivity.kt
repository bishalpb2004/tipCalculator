package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{calculateTip()}
    }

    private fun calculateTip() {
        val stringinTextField=binding.costOfService.text.toString()
        val cost=stringinTextField.toDoubleOrNull()
        if (cost==null){
            binding.tipAmount.text=" "
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage=when(selectedId)
        {
            R.id.option_twenty_percent->0.2
            R.id.option_eighteen_percent->0.18
            else->0.15
        }
        var tipCalculate=cost*tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tipCalculate= kotlin.math.ceil(tipCalculate)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tipCalculate)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}
