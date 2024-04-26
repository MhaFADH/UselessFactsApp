package com.example.uselessfacts.ui.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uselessfacts.model.RandomFactViewModel


@Composable
fun HistoryScreen(navHostController: NavHostController,viewModel:RandomFactViewModel) {
    var refreshSwitch by remember { mutableStateOf(false)}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { navHostController.popBackStack()}) {
                Text("Go Back")
            }
            Button(onClick = {
                viewModel.factHistory.clear()
                refreshSwitch = !refreshSwitch
            }) {
                Text("Clear")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn {
            var switch = refreshSwitch
            items(viewModel.factHistory.size) { index ->
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = viewModel.factHistory[index], textAlign= TextAlign.Center, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Divider()
            }
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


