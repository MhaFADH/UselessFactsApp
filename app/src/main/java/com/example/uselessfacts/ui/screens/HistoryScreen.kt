package com.example.uselessfacts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uselessfacts.model.RandomFactViewModel


@Composable
fun HistoryScreen(navHostController: NavHostController,viewModel:RandomFactViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navHostController.popBackStack()}) {
            Text("Go Back")
        }
            repeat(viewModel.factHistory.size) { index ->
                Text(text = viewModel.factHistory[index])
            }

    }
}

@Composable
@Preview
fun HistoryScreenPreview() {
    val navHostController : NavHostController = rememberNavController()
    val factViewModel : RandomFactViewModel = viewModel()

    HistoryScreen(navHostController,factViewModel)
}


