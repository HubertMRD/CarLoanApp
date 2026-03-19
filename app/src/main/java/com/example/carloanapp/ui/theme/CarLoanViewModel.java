package com.example.carloanapp.ui.theme;

public class carLoanViewModel: VewModel() {
    var price by  mutableStateOf("")
    var downPayment by  mutableStateOf("")
    var selectedYears by  mutableStateOf(1)
    var interestRate by  mutableStateOf(5f)
    var monthlyPayment by  mutableStateOf(0.0)
}
