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
import com.example.uselessfacts.navigation.Routes


@Composable
fun FactScreen(navHostController: NavHostController,viewModel:RandomFactViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = viewModel.fact,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.fetchRandomFact() }) {
            Text("Get Random Fact")
        }
        Button(onClick = { viewModel.fetchRandomFact("en","today") }) {
            Text("Get Today Fact")
        }
        Button(onClick = { navHostController.navigate(Routes.HistoryScreen.route)}) {
            Text("History")
        }
        LazyColumn {
            items(viewModel.factHistory.size) { index ->
                Text(text = viewModel.factHistory[index])
            }
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


