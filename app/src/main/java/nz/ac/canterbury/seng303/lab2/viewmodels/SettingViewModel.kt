package nz.ac.canterbury.seng303.lab2.viewmodels

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nz.ac.canterbury.seng303.lab2.R


class SettingViewModel : ViewModel() {
    private val _isDarkMode = MutableStateFlow(false) // Default to light mode
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    // Function to toggle the theme
    fun toggleTheme() {
        viewModelScope.launch {
            _isDarkMode.value = !_isDarkMode.value
        }
    }
}