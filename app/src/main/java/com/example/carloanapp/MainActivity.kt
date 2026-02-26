package com.example.carloanapp

import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarloanApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CarloanApp(modifier: Modifier = Modifier) {

    var price by remember { mutableStateOf("") }
    var downPayment by remember { mutableStateOf("") }
    var selectedYears by remember { mutableStateOf(1) }
    var interestRate by remember { mutableStateOf(5f) }
    var monthlyPayment by remember { mutableStateOf(0.0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Image 
        Image(
            painter = painterResource(R.drawable.car),
            contentDescription = "Graduation cap",

        )

        Text(
            text = "Car Loan Calculator",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

       
        Text("Car Price")
        TextField(
            value = price,
            onValueChange = { price = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(12.dp))

      
        Text("Down Payment")
        TextField(
            value = downPayment,
            onValueChange = { downPayment = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(20.dp))

        
        Text("Loan Length (Years)", style = MaterialTheme.typography.titleMedium)

        val yearOptions = listOf(1, 2, 3, 4)

        yearOptions.forEach { year ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.selectable(
                    selected = selectedYears == year,
                    onClick = { selectedYears = year },
                    role = Role.RadioButton
                )
            ) {
                RadioButton(
                    selected = selectedYears == year,
                    onClick = null
                )
                Text("$year years")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Interest Rate: ${String.format("%.1f", interestRate)}%")
        Slider(
            value = interestRate,
            onValueChange = { interestRate = it },
            valueRange = 0f..15f
        )

        Spacer(modifier = Modifier.height(20.dp))

        
        Button(
            onClick = {

                val carPrice = price.toDoubleOrNull() ?: 0.0
                val down = downPayment.toDoubleOrNull() ?: 0.0
                val loanAmount = carPrice - down

                val monthlyRate = interestRate / 100 / 12
                val numberOfPayments = selectedYears * 12

                monthlyPayment =
                    if (monthlyRate.toDouble() == 0.0) {
                        loanAmount / numberOfPayments
                    } else {
                        (monthlyRate * loanAmount) /
                                (1 - Math.pow((1 + monthlyRate).toDouble(), -numberOfPayments.toDouble()))
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Monthly Payment")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Monthly Payment: $${String.format("%.2f", monthlyPayment)}",
            style = MaterialTheme.typography.titleLarge
        )
    }
}