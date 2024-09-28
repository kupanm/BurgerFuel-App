package nz.ac.canterbury.seng303.lab2.models

class MenuItem(
    val id: Int, /* Unique identifier for the menu item */
    val icon: Int, /* This is the image for the menu item. Always displayed */
    val name: String, /* This is the name for the menu item. Always displayed */
    val description: String /* This is the description. Only shown when user clicks on menu item */
) {
}