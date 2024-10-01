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
): Identifiable {

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
                ),

                MenuItem(
                    2,
                    R.drawable.rebel_feed_combo,
                    "Rebel Feed Combo",
                    "The Iron Rebel, Spud Fries with Aioli and a Coke range product",
                    26.50,
                    "Specials"
                ),

                MenuItem(
                    3,
                    R.drawable.ranch_wrangler_burger,
                    "Ranch Wrangler",
                    "Plan*t crispy Chick*n* patty, bread & butter pickles, vegan Ranch sauce, vegan Buffalo sauce, Tajin & Lime seasoning, mini wholemeal bun.",
                    13.90,
                    "Specials"
                ),

                MenuItem(
                    4,
                    R.drawable.iron_rebel_burger,
                    "Iron Rebel",
                    "100% pure grass-fed NZ beef, smokey, slow cooked pulled beef, marinated in sticky BBQ sauce, melted cheddar, crispy lettuce, tomato, onion, free-range Aioli and a wholemeal bun. ",
                    21.50,
                    "Specials"
                ),

                MenuItem(
                    5,
                    R.drawable.chev_brulee_drink,
                    "Chev Brulee",
                    " All natural BurgerFuel Whip blended with creamy custard and swirls of Crème Brûlée Syrup. *This shake contains custard, it cannot be made dairy-free. ",
                    9.50,
                    "Specials"
                ),

                MenuItem(
                    6,
                    R.drawable.loaded_fries,
                    "Loaded Off-Roader Fries",
                    "Seared bacon bits, BBQ sauce, cheese sauce, BurgerFuel Aioli, crispy onions and Spud Fries ",
                    9.80,
                    "Specials"
                ),

                MenuItem(
                    7,
                    R.drawable.specials,
                    "Specials",
                    "Specials Icon",
                    0.00,
                    "Icons"
                ),

                MenuItem(
                    8,
                    R.drawable.beef,
                    "NZ Beef",
                    "NZ Beef Icon",
                    0.00,
                    "Icons"
                ),

                MenuItem(
                    9,
                    R.drawable.chicken,
                    "Free Range Chicken",
                    "Free Range Chicken Icon",
                    0.00,
                    "Icons"
                    ),

                MenuItem(
                    10,
                    R.drawable.plant,
                    "Plant-Powered",
                    "Plant-Powered Icon",
                    0.00,
                    "Icons"
                ),

                MenuItem(
                    11,
                    R.drawable.minis,
                    "Minis",
                    "Minis Icon",
                    0.00,
                    "Icons"
                ),

                MenuItem(
                    12,
                    R.drawable.sides,
                    "Sides",
                    "Sides Icon",
                    0.00,
                    "Icons"
                ),

                MenuItem(
                    13,
                    R.drawable.drinks,
                    "Thickshakes & Drinks",
                    "Thickshakes & Drinks Icon",
                    0.00,
                    "Icons"
                ),

                MenuItem(
                    14,
                    R.drawable.kids,
                    "Kids Ink. Meals",
                    "Kids Ink. Meals Icon",
                    0.00,
                    "Icons"
                ),

                MenuItem(
                    15,
                    R.drawable.sauces,
                    "Sauces",
                    "Sauces Icon",
                    0.00,
                    "Icons"
                )
            )
        }
    }

    override fun getIdentifier(): Int {
        return id;
    }
}