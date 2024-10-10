package nz.ac.canterbury.seng303.lab2.models

class MenuStorageItem(
    val id: Int, /* Unique identifier for the menu item */
    var amount: Int, /* Amount of items for cart storage */
    val icon: Int, /* This is the image for the menu item. Always displayed */
    val name: String, /* This is the name for the menu item. Always displayed */
    val description: String, /* This is the description. Only shown when user clicks on menu item */
    val price: Double, /* This is the price of the menu item. Shown on the preview of a menu item and when user clicks on menu item. MAYBE CHANGE TYPE TO CURRENCY*/
): Identifiable {


    override fun getIdentifier(): Int {
        return id;
    }
}