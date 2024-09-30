package nz.ac.canterbury.seng303.lab2.models

import nz.ac.canterbury.seng303.lab2.R

/* This is for the Scrollable row on the home page */
class MenuIcon(
    val id: Int, /* Unique identifier for the menu icon */
    val defaultIcon: Int, /* This is the default icon */
    val clickedIcon: Int, /* This is the icon displayed when the icon is clicked */
    val text: String, /* This is the text below the icon */
): Identifiable {

    companion object {
        fun getMenuIcons(): List<MenuIcon> {
            return listOf(
                MenuIcon(
                    1,
                    R.drawable.specials,
                    R.drawable.specials_selected,
                    "Specials"
                ),
                MenuIcon(
                    2,
                    R.drawable.beef,
                    R.drawable.beef_selected,
                    "NZ Beef"
                ),
                MenuIcon(
                    3,
                    R.drawable.chicken,
                    R.drawable.chicken_selected,
                    "Free Range Chicken"
                ),
                MenuIcon(
                    4,
                    R.drawable.plant,
                    R.drawable.plant_selected,
                    "Plant-Powered"
                ),
                MenuIcon(
                    5,
                    R.drawable.minis,
                    R.drawable.minis_selected,
                    "Minis"
                ),
                MenuIcon(
                    6,
                    R.drawable.sides,
                    R.drawable.sides_selected,
                    "Sides"
                ),
                MenuIcon(
                    7,
                    R.drawable.drinks,
                    R.drawable.drinks_selected,
                    "Thickshakes & Drinks"
                ),
                MenuIcon(
                    8,
                    R.drawable.kids,
                    R.drawable.kids_selected,
                    "Kids Ink. Meals"
                ),
                MenuIcon(
                    9,
                    R.drawable.sauces,
                    R.drawable.sauces_selected,
                    "Sauces"
                ),

            )
        }
    }

    override fun getIdentifier(): Int {
        return id
    }
}