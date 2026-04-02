package com.example.carloanapp

import android.content.res.Configuration
import android.os.Bundle
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carloanapp.ui.theme.CarloanAppTheme

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
fun CarloanApp(
    modifier: Modifier = Modifier,
    carLoanViewModel: CarLoanViewModel = viewModel()
) {
    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
        CarloanAppPortrait(modifier, carLoanViewModel)
    } else {
        CarloanAppLandscape(modifier, carLoanViewModel)
    }
}

@Composable
fun CarloanAppPortrait(modifier: Modifier, vm: CarLoanViewModel) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.car),
            contentDescription = "Car"
        )

        Text("Car Loan Calculator", modifier = Modifier.padding(bottom = 20.dp))

        Text("Car Price")
        TextField(
            value = vm.price,
            onValueChange = { vm.price = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Down Payment")
        TextField(
            value = vm.downPayment,
            onValueChange = { vm.downPayment = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Loan Length (Years)")
        val yearOptions = listOf(1, 2, 3, 4)

        yearOptions.forEach { year ->
            Row(
                modifier = Modifier.selectable(
                    selected = vm.selectedYears == year,
                    onClick = { vm.selectedYears = year }
                )
            ) {
                RadioButton(selected = vm.selectedYears == year, onClick = null)
                Text("$year years")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Interest Rate: ${String.format("%.1f", vm.interestRate)}%")
        Slider(
            value = vm.interestRate,
            onValueChange = { vm.interestRate = it },
            valueRange = 0f..15f
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { vm.calculate() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Monthly Payment")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Monthly Payment: $${String.format("%.2f", vm.monthlyPayment)}")
    }
}


@Composable
fun CarloanAppLandscape(modifier: Modifier, vm: CarLoanViewModel) {

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Image(
            painter = painterResource(R.drawable.car),
            contentDescription = "Car",
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        )

      
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(10.dp)
        ) {

            Text("Car Loan Calculator")

            Text("Car Price")
            TextField(
                value = vm.price,
                onValueChange = { vm.price = it }
            )

            Text("Down Payment")
            TextField(
                value = vm.downPayment,
                onValueChange = { vm.downPayment = it }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text("Interest: ${String.format("%.1f", vm.interestRate)}%")
            Slider(
                value = vm.interestRate,
                onValueChange = { vm.interestRate = it }
            )

            Button(onClick = { vm.calculate() }) {
                Text("Calculate")
            }

            Text("Monthly: $${String.format("%.2f", vm.monthlyPayment)}")
        }
    }
}

/*

class CarLoanViewModel : ViewModel() {

    // User inputs
    var price by mutableStateOf("")
    var downPayment by mutableStateOf("")
    var selectedYears by mutableStateOf(1)
    var interestRate by mutableStateOf(5f)

    // Output
    var monthlyPayment by mutableStateOf(0.0)

    // Calculation function
    fun calculate() {
        val carPrice = price.toDoubleOrNull() ?: 0.0
        val down = downPayment.toDoubleOrNull() ?: 0.0
        val loanAmount = carPrice - down

        val monthlyRate = interestRate / 100 / 12
        val numberOfPayments = selectedYears * 12

        monthlyPayment =
            if (monthlyRate == 0f) {
                loanAmount / numberOfPayments
            } else {
                (monthlyRate * loanAmount) /
                        (1 - (1 + monthlyRate).pow(-numberOfPayments))
            }
    }
}



*/





