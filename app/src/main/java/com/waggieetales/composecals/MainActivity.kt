package com.waggieetales.composecals

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.waggieetales.composecals.ui.CalculatorMemoryState
import com.waggieetales.composecals.ui.DisplayModel
import com.waggieetales.composecals.data.CalculatorOperation
import com.waggieetales.composecals.ui.theme.ComposeCalsTheme
import com.waggieetales.composecals.ui.theme.freeCamFont
import com.waggieetales.composecals.util.CalculatorUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCalsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorView(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth()
                            .background(Color.DarkGray)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorView(modifier: Modifier = Modifier) {

    var state =
        remember {
            mutableStateOf(DisplayModel())
        }

    var calculatorModel = CalculatorMemoryState()

    Column {
        toolbarSpace()
        ResultDisplayView(state,calculatorModel )
        ButtonsRow(1, CalculatorOperation.ADD, state, calculatorModel)
        SpacerCommon()
        ButtonsRow(4, CalculatorOperation.SUB, state, calculatorModel)
        SpacerCommon()
        ButtonsRow(7, CalculatorOperation.MULT, state, calculatorModel)
        SpacerCommon()
        LastRow(0, CalculatorOperation.EQUAL, state, calculatorModel)
        SpacerCommon()
    }
}



@Composable
fun SpacerCommon() {
    Spacer(Modifier.padding(5.dp))
}

@Composable
fun ButtonsRow(
    startValue: Int,
    symbol: CalculatorOperation,
    data: MutableState<DisplayModel>,
    calculatorModel: CalculatorMemoryState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(4) { index ->
            ElevatedButton(
                onClick = {

                    if (index == 3) {
                        //calculatorModel.operation = symbol
                        calculatorModel.currentInput += symbol.code
                        data.value = data.value.copy(resultValue = calculatorModel.currentInput)
                    } else {

                        calculatorModel.currentInput += (index + startValue).toString()

                        //creating immutable object to update the value dependent views
                        data.value = data.value.copy(resultValue = calculatorModel.currentInput)

                        Log.e(
                            "MyApp",
                            "===C : ${calculatorModel.currentInput}, P : ${calculatorModel.previousInput} "
                        )
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                if (index == 3) {
                    Text(symbol.code)
                } else {
                    Text("${index + startValue}")
                }
            }
        }
    }
}

@Composable
fun LastRow(
    startValue: Int,
    symbol: CalculatorOperation,
    data: MutableState<DisplayModel>,
    calculatorModel: CalculatorMemoryState
) {
    val rowModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)

    val rowHorizontalArrangement = Arrangement.spacedBy(16.dp)

    val calculatorUtil = CalculatorUtil()

    Row(
        modifier = rowModifier,
        horizontalArrangement = rowHorizontalArrangement
    ) {
        //Todo: Re-usablity is not working
        //LastRowButtonConfig(rowModifier.weight(1f))
        //LastRowButtonConfig(rowModifier.weight(1f))
        //LastRowButtonConfig(rowModifier.weight(1f))
        //LastRowButtonConfig(rowModifier.weight(1f))


        ElevatedButton(
            onClick = { },
            modifier = Modifier.weight(1f)
        ) {
            Text("")
        }

        ElevatedButton(
            onClick = { },
            modifier = Modifier.weight(1f)
        ) {
            Text("0")
        }

        ElevatedButton(
            onClick = { },
            modifier = Modifier.weight(1f)
        ) {
            Text("")
        }

        ElevatedButton(
            onClick = {
                val result = calculatorUtil.evaluateCals(calculatorModel.currentInput)
                calculatorModel.currentInput = result.toString()

                //creating immutable object to update the value dependent views
                data.value = data.value.copy(resultValue = result.toString())
            },
            modifier = Modifier.weight(1f)
        ) {
            Text("=")
        }
    }
}

//TODO: Need to check this why existing modifier is not able to use here
/*@Composable
fun LastRowButtonConfig(modifier: Modifier){
 ElevatedButton(
 onClick = { *//* handle click *//* },
 modifier = modifier
 ) {
 Text("1")
 }
}*/

@Composable
fun ResultDisplayView(data: MutableState<DisplayModel>, calculatorModel: CalculatorMemoryState ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color.Green, RoundedCornerShape(5.dp))
            .padding(6.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            //TODO : To set value dynamically
            text = data.value.resultValue,
            fontSize = 20.sp,
            fontFamily = freeCamFont
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorViewPreview() {
    ComposeCalsTheme {
        CalculatorView()
    }
}

@Composable
fun toolbarSpace() {
    SpacerCommon()
    SpacerCommon()
    SpacerCommon()
}


