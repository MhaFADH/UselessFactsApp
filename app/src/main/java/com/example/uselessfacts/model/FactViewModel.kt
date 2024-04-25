package com.example.uselessfacts.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uselessfacts.services.FactsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomFactViewModel : ViewModel() {
    var fact by mutableStateOf("Press the button to get a random fact")
    var factHistory = mutableListOf<String>()
    var selectedLang by  mutableStateOf("en")

    fun fetchRandomFact(lang:String = "en",option:String = "random") {
        viewModelScope.launch(Dispatchers.Default) {
            fact = try {
                val response = FactsAPI.sendGet(lang,option)
                factHistory.add(response.text)
                response.text
            } catch (e: Exception) {
                "Error fetching fact: ${e.message}"
            }

        }
    }
}