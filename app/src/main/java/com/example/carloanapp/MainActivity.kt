package com.example.carloanapp

import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carloanapp.ui.theme.CarloanAppTheme
import kotlinx.coroutines.selects.select
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarloanAppTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    CarloanApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CarloanApp(name: String, modifier: Modifier = Modifier) {
    var price by remember { mutableStateOf("") }
    var payment by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf("") }
    var loan by remember { mutableStateOf("1 year") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(

        )
        {
            Text(text = "Enter the payment amount: ")
            TextField(
                value = price,
                onValueChange = { price = it },
                modifier = modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
    Row(

    )
    {
        Text(text = "Enter the down payment: ")
        TextField(
            value = payment,
            onValueChange = { payment = it },
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
    Column(

    ) {
        RadioGroup(
            labelText = "length of the loan",
            radioOprions = listOf("1 year", "2 years", "3 years", "4 years"),
            selectedOption = loan,
            onSelected = { loan = it },
            modifier = modifier
        )
    }
}

@Composable
fun RadioGroup(
    labelText: Text: String,
    radioOptions: List<String>,
    selectedOption: String,
    onSeleted:(String) -> Unit,
    modifier: Modifier= Modifier
){
    var selectedOption by remember { mutableStateOf(selectedValue) }
    val isSelectedOption: (String) -> Boolean = {selectedOption ==it}
    Column()
    {
        Text(labelText)
        radioOptions.forEach { option ->
            Row(
                modifier=modifier
                    .selectable(
                        selected = isSelctionOption(option),
                        onClick = {onSeleted(option) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelctionOption(option),
                    onClick = null,
                    modifier = modifier.padding(end=8.dp)
                )
                Text(
                    text = option,
                    modifier= modifier.fillMaxWidth()
                )
            }
        }

    }
}
