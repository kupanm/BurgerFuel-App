package nz.ac.canterbury.seng303.lab2.models

import android.icu.util.Currency
import nz.ac.canterbury.seng303.lab2.R

class MenuItem(
    val id: Int, /* Unique identifier for the menu item */
    val icon: Int, /* This is the image for the menu item. Always displayed */
    val name: String, /* This is the name for the menu item. Always displayed */
    val description: String, /* This is the description. Only shown when user clicks on menu item */
    val price: Double, /* This is the price of the menu item. Shown on the preview of a menu item and when user clicks on menu item. MAYBE CHANGE TYPE TO CURRENCY*/
    val type: String, /* This is the type of menu item it is. Special, NZ Beef, Free Range Chicken etc. */
) {
    companion object {
        fun getMenuItems(): List<MenuItem> {
            return listOf(
                MenuItem(
                    1,
                    R.drawable.ranch_feed_combo,
                    "Ranch Feed Combo",
                    "Ranch Wrangler burger, Spud Fries w Aioli & Classic Coke* (*or any Classic Soda from the Coca Cola 330ml glass bottle range.",
                    20.80,
                    "Specials"
                )
            )
        }
    }
}