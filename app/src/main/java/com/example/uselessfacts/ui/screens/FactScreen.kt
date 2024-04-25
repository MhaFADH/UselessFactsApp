package com.example.uselessfacts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uselessfacts.model.RandomFactViewModel
import com.example.uselessfacts.navigation.Routes




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactScreen(navHostController: NavHostController,viewModel:RandomFactViewModel) {

    var expanded by remember { mutableStateOf(false) }
    val langs = listOf("en","de")
    var selectedLang by remember { mutableStateOf("en") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(expanded = expanded,
            onExpandedChange ={expanded = !expanded} ) {
            TextField(
                modifier = Modifier.menuAnchor().width(90.dp),
                value = selectedLang,
                onValueChange = {selectedLang = it},
                readOnly = true,
                trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                )

            ExposedDropdownMenu(expanded = expanded,
                onDismissRequest = {expanded = false}) {
                langs.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedLang = selectionOption
                            expanded = false
                        },
                        text = { Text(text = selectionOption) }
                    )
                }
        }
        }

        Text(
            text = viewModel.fact,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.fetchRandomFact(lang = selectedLang) }) {
            Text("Get Random Fact")
        }
        Button(onClick = { viewModel.fetchRandomFact(selectedLang,"today") }) {
            Text("Get Today Fact")
        }
        Button(onClick = { navHostController.navigate(Routes.HistoryScreen.route)}) {
            Text("History")
        }
    }
}

@Composable
@Preview
fun FactScreenPreview() {
    val navHostController : NavHostController = rememberNavController()
    val factViewModel : RandomFactViewModel = viewModel()

    FactScreen(navHostController,factViewModel)
}


