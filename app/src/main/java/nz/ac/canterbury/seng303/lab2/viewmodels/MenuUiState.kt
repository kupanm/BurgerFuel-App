package nz.ac.canterbury.seng303.lab2.viewmodels

import nz.ac.canterbury.seng303.lab2.R
import nz.ac.canterbury.seng303.lab2.models.MenuIcon

data class MenuUiState(
    var currentSelectedIcon: MenuIcon = MenuIcon.getMenuIcons().first() /* Will get the first menu icon as the default selected icon */
)
