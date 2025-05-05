package com.waggieetales.composecals.ui.data


data class CalculatorMemoryState(
    val currentInput: String = "",
    val previousInput: String = "",
    val operation: CalculatorOperation? = null,
    val result: String = "",

    //Integer types
    var currentValue : Int = 0,
    var newValue : Int = 0,
)

data class DisplayModel(
    var resultValue :  String = ""
)