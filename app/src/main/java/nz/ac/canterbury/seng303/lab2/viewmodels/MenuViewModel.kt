package nz.ac.canterbury.seng303.lab2.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nz.ac.canterbury.seng303.lab2.models.MenuIcon

class MenuViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()

    fun newSelectedIcon(newIcon: MenuIcon) {
        _uiState.value = MenuUiState(currentSelectedIcon = newIcon)
    }
}