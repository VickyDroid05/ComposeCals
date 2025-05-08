package com.waggieetales.composecals.ui

import com.waggieetales.composecals.data.CalculatorOperation


data class CalculatorMemoryState(
    var currentInput: String = "",
    var previousInput: String = "",
    var operation: CalculatorOperation? = null,
    var result: String = "",

    //Integer types
    var currentValue : Int = 0,
    var newValue : Int = 0,
)

data class DisplayModel(
    var resultValue :  String = "1234"
)