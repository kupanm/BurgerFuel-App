package nz.ac.canterbury.seng303.lab2.models

import android.icu.util.Currency
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import nz.ac.canterbury.seng303.lab2.R

class MenuItem(
    val id: Int, /* Unique identifier for the menu item */
    var amount: MutableState<Int>, /* Amount of items for cart storage */
    val icon: Int, /* This is the image for the menu item. Always displayed */
    val name: String, /* This is the name for the menu item. Always displayed */
    val description: String, /* This is the description. Only shown when user clicks on menu item */
    val price: Double, /* This is the price of the menu item. Shown on the preview of a menu item and when user clicks on menu item. MAYBE CHANGE TYPE TO CURRENCY*/
    val type: Set<String>, /* This is the type of menu item it is. Special, NZ Beef, Free Range Chicken etc. */
): Identifiable {

    fun inc() {
        this.amount.value++
    }

    fun dec() {
        if(this.amount.value > 1) {
            this.amount.value--
        }
    }

    companion object {
        fun getMenuItems(): List<MenuItem> {
            return listOf(
                MenuItem(
                    1,
                    mutableStateOf(1),
                    R.drawable.ranch_feed_combo,
                    "Ranch Feed",
                    "Ranch Wrangler burger, Spud Fries w Aioli & Classic Coke* (*or any Classic Soda from the Coca Cola 330ml glass bottle range.",
                    20.80,
                    setOf("Specials")
                ),

                MenuItem(
                    2,
                    mutableStateOf(1),
                    R.drawable.rebel_feed_combo,
                    "Rebel Feed",
                    "The Iron Rebel, Spud Fries with Aioli and a Coke range product",
                    26.50,
                    setOf("Specials")
                ),

                MenuItem(
                    3,
                    mutableStateOf(1),
                    R.drawable.ranch_wrangler_burger,
                    "Ranch Wrangler",
                    "Plan*t crispy Chick*n* patty, bread & butter pickles, vegan Ranch sauce, vegan Buffalo sauce, Tajin & Lime seasoning, mini wholemeal bun.",
                    13.90,
                    setOf("Specials", "Plant-Powered", "Minis")
                ),

                MenuItem(
                    4,
                    mutableStateOf(1),
                    R.drawable.iron_rebel_burger,
                    "Iron Rebel",
                    "100% pure grass-fed NZ beef, smokey, slow cooked pulled beef, marinated in sticky BBQ sauce, melted cheddar, crispy lettuce, tomato, onion, free-range Aioli and a wholemeal bun. ",
                    21.50,
                    setOf("Specials", "NZ Beef")
                ),

                MenuItem(
                    5,
                    mutableStateOf(1),
                    R.drawable.chev_brulee_drink,
                    "Chev Brulee",
                    " All natural BurgerFuel Whip blended with creamy custard and swirls of Crème Brûlée Syrup. *This shake contains custard, it cannot be made dairy-free. ",
                    9.50,
                    setOf("Specials")
                ),

                MenuItem(
                    6,
                    mutableStateOf(1),
                    R.drawable.loaded_fries,
                    "Loaded Off-Roader Fries",
                    "Seared bacon bits, BBQ sauce, cheese sauce, BurgerFuel Aioli, crispy onions and Spud Fries ",
                    9.80,
                    setOf("Specials", "Sides")
                ),

                MenuItem(
                    7,
                    mutableStateOf(1),
                    R.drawable.muscle_single,
                    "American Muscle Single",
                    "NZ beef, cheddar, bread and butter pickles, BurgerFuel mustard, relish and BurgerFuel Aioli.",
                    14.00,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    8,
                    mutableStateOf(1),
                    R.drawable.muscle_double,
                    "American Muscle Double",
                    "Double NZ beef, double cheddar, bread and butter pickles, BurgerFuel mustard, relish and BurgerFuel Aioli.",
                    19.50,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    9,
                    mutableStateOf(1),
                    R.drawable.c_n_cheese,
                    "C N Cheese",
                    "NZ beef, cheddar, truffle mascarpone, parmesan, salad, relish and BurgerFuel Aioli.",
                    16.00,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    10,
                    mutableStateOf(1),
                    R.drawable.bio_fuel,
                    "Bio Fuel",
                    "NZ beef, egg, grated beetroot w/chia, salad, relish and BurgerFuel Aioli.",
                    16.00,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    11,
                    mutableStateOf(1),
                    R.drawable.bastard,
                    "Bastard",
                    "NZ beef, cheddar, bacon, grated beetroot w/chia, pineapple, avocado, salad, relish and BurgerFuel Aioli.",
                    19.50,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    12,
                    mutableStateOf(1),
                    R.drawable.ford_freakout,
                    "Ford Freakout",
                    "NZ beef, bacon, avocado, salad, relish and BurgerFuel Aioli.",
                    16.60,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    13,
                    mutableStateOf(1),
                    R.drawable.bacon_bbq_roadster,
                    "Bacon BBQ Roadster",
                    "NZ beef, cheddar, bacon, battered onion rings, BBQ sauce, salad, relish and BurgerFuel Aioli.",
                    19.50,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    14,
                    mutableStateOf(1),
                    R.drawable.hamburgini_w_cheese,
                    "Hamburgini with Cheese",
                    "NZ beef, cheddar, pickles, relish and BurgerFuel Aioli on a mini wholemeal bun.",
                    8.90,
                    setOf("NZ Beef", "Minis")
                ),

                MenuItem(
                    15,
                    mutableStateOf(1),
                    R.drawable.bacon_backfire,
                    "Bacon Backfire",
                    "Grilled chicken, bacon, brie, salad, relish and BurgerFuel Aioli.",
                    17.40,
                    setOf("Free Range Chicken")
                ),

                MenuItem(
                    16,
                    mutableStateOf(1),
                    R.drawable.burnout,
                    "Burnout",
                    "Grilled chicken, bacon, avocado, blue cheese sauce, BurgerFuel mustard, salad, relish and BurgerFuel Aioli.",
                    17.60,
                    setOf("Free Range Chicken")
                ),

                MenuItem(
                    17,
                    mutableStateOf(1),
                    R.drawable.modifried_thunderbird,
                    "Modifried Thunderbird",
                    "Crispy buttermilk free-range fried chicken with a jalapeño and corn crumb, sliced jalapeño, salad, relish and BurgerFuel Chipotle Aioli.",
                    17.40,
                    setOf("Free Range Chicken")
                ),

                MenuItem(
                    18,
                    mutableStateOf(1),
                    R.drawable.chook_royale,
                    "Modifried Thunderbird",
                    "Grilled chicken tenderloins, seasoned with sea salt, garlic and rosemary, pineapple, salad, relish and BurgerFuel Aioli on a mini wholemeal bun.",
                    10.50,
                    setOf("Free Range Chicken", "Minis")
                ),

                MenuItem(
                    19,
                    mutableStateOf(1),
                    R.drawable.v8_vegan,
                    "V8 Vegan",
                    "Crumbed pumpkin, carrot, chickpea & ginger bites, vegan cheddar, avocado, grated beetroot w/chia, salad, relish and BurgerFuel Vegan Aioli.",
                    15.90,
                    setOf("Plant-Powered")
                ),

                MenuItem(
                    20,
                    mutableStateOf(1),
                    R.drawable.v_twin_vege,
                    "V-Twin Vege",
                    "Mushroom, kumara, chickpea & basil patty, vegetarian cheddar, truffle mascarpone, plum sauce, salad, relish and BurgerFuel Aioli *Remove mascarpone, switch to BurgerFuel Vegan Aioli & vegan cheddar to make it vegan.",
                    14.90,
                    setOf("Plant-Powered")
                ),

                MenuItem(
                    21,
                    mutableStateOf(1),
                    R.drawable.spud_fries_aioli,
                    "Spud Fries with Aioli",
                    "Thick cut potato served with BurgerFuel Aioli.",
                    6.90,
                    setOf("Sides")
                ),

                MenuItem(
                    22,
                    mutableStateOf(1),
                    R.drawable.kumara_fries_aioli,
                    "Kumara Fries with Aioli",
                    "Thick cut golden kūmara served with BurgerFuel Aioli.",
                    7.90,
                    setOf("Sides")
                ),

                MenuItem(
                    23,
                    mutableStateOf(1),
                    R.drawable.smash_browns_aioli,
                    "Smash Browns with Aioli",
                    "Golden, crispy hash bites served with BurgerFuel Aioli.",
                    7.90,
                    setOf("Sides")
                ),

                MenuItem(
                    24,
                    mutableStateOf(1),
                    R.drawable.choppers,
                    "Choppers",
                    "Crispy southern fried chicken pieces drizzled with Sriracha and BurgerFuel Aioli.",
                    12.90,
                    setOf("Sides")
                ),

                MenuItem(
                    25,
                    mutableStateOf(1),
                    R.drawable.chicken_fenders,
                    "Chicken Fenders",
                    "4 or 6 grilled free range skinless chicken tenderloins, seasoned with sea salt, garlic and rosemary, served with BurgerFuel Lemon Aioli or Chipotle Aioli.",
                    10.90,
                    setOf("Sides")
                ),

                MenuItem(
                    26,
                    mutableStateOf(1),
                    R.drawable.motobites,
                    "Motobites",
                    "Crumbed pumpkin, carrot, chickpea & ginger bites, served with BurgerFuel Lemon Aioli or Chipotle Aioli",
                    8.90,
                    setOf("Sides")
                ),

                MenuItem(
                    27,
                    mutableStateOf(1),
                    R.drawable.thickshake,
                    "Thickshake",
                    "Milk thickshakes made with all-natural BurgerFuel Whip.",
                    8.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    28,
                    mutableStateOf(1),
                    R.drawable.soy_thickshake,
                    "Soy Thickshake",
                    "Soy thickshakes made with soy ice cream",
                    9.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    29,
                    mutableStateOf(1),
                    R.drawable.rescued_cherry_cola,
                    "Cherry Cola",
                    "A BurgerFuel & Citizen collab. Combines the dark, rich flavour of rescued Central Otago Cherries & rum wash (The by-product botanicals from making rum).",
                    5.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    30,
                    mutableStateOf(1),
                    R.drawable.rescued_cherry_lemonade,
                    "Cherry Lemonade",
                    "A BurgerFuel & Citizen collab brings you a refreshing cherry pink lemonade. Every can saves 12 NZ cherries from going to waste.",
                    5.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    31,
                    mutableStateOf(1),
                    R.drawable.rescued_orangeade,
                    "Cherry Orangeade",
                    "A BurgerFuel & Citizen collab brings you a refreshing orange lemonade. Every can saves 2 NZ oranges from going to waste.",
                    5.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    32,
                    mutableStateOf(1),
                    R.drawable.coke_330ml,
                    "Coke 330ml",
                    "Coca-Cola 330ml",
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    33,
                    mutableStateOf(1),
                    R.drawable.coke_zero_sugar_330ml,
                    "Coke Zero Sugar 330ml",
                    "Coca-Cola Zero Sugar 330ml",
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    34,
                    mutableStateOf(1),
                    R.drawable.diet_coke_330ml,
                    "Diet Coke 330ml",
                    "Diet Coca-Cola 330ml",
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    35,
                    mutableStateOf(1),
                    R.drawable.sprite_330ml,
                    "Sprite 330ml",
                    "Sprite 330ml",
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    36,
                    mutableStateOf(1),
                    R.drawable.l_n_p_330ml,
                    "L&P 330ml",
                    "L&P 330ml",
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    37,
                    mutableStateOf(1),
                    R.drawable.fanta_330ml,
                    "Fanta 330ml",
                    "Fanta 330ml",
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    38,
                    mutableStateOf(1),
                    R.drawable.organic_apple_orange_mango,
                    "mOst Oraganic Apple Orange & Mango",
                    "Organic Apple Orange & Mango",
                    5.90,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    39,
                    mutableStateOf(1),
                    R.drawable.organic_apple_feijoa,
                    "mOst Oraganic Apple & Feijoa",
                    "Oraganic Apple & Feijoa Juice",
                    5.90,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    40,
                    mutableStateOf(1),
                    R.drawable.organic_apple_blackcurrant,
                    "mOst Organic Apple & Blackcurrant",
                    "mOst Organic Apple & Blackcurrant juice",
                    5.90,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    41,
                    mutableStateOf(1),
                    R.drawable.kiwi_blue600ml,
                    "Kiwi Blue 600ml",
                    "Kiwi Blue 600ml",
                    5.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    42,
                    mutableStateOf(1),
                    R.drawable.keri_kids_apple,
                    "Keri Kids Apple 250ml",
                    "Keri Kids Apple Juice",
                    3.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    43,
                    mutableStateOf(1),
                    R.drawable.keri_kids_apple_blackcurrant,
                    "Keri Kids Apple & Blackcurrant 250ml",
                    "Keri Kids Apple & Blackcurrant",
                    3.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    44,
                    mutableStateOf(1),
                    R.drawable.kids_ink_cheeseburger_meal,
                    "Kids Ink. Cheeseburger Meal",
                    "Kids Cheeseburger, spud fries with free range BurgerFuel Aioli or tomato sauce, and a small juice.",
                    13.50,
                    setOf("Kids Ink. Meals")
                ),

                MenuItem(
                    45,
                    mutableStateOf(1),
                    R.drawable.kids_ink_mini_motobites_meal,
                    "Kids Ink. Mini Motobites Meal",
                    " Small Motobites, spud fries with free range BurgerFuel Aioli or tomato sauce, and a small juice.",
                    13.50,
                    setOf("Kids Ink. Meals")
                ),

                MenuItem(
                    46,
                    mutableStateOf(1),
                    R.drawable.kids_ink_teeny_choppers_meal,
                    "Kids Ink. Teeny Choppers Meal",
                    "Small Choppers, spud fries with free range BurgerFuel Aioli or tomato sauce, and a small juice.",
                    13.50,
                    setOf("Kids Ink. Meals")
                ),

                MenuItem(
                    47,
                    mutableStateOf(1),
                    R.drawable.aioli,
                    "BurgerFuel Aioli",
                    "Free Range BurgerFuel Aioli",
                    1.80,
                    setOf("Sauces")
                ),

                MenuItem(
                    48,
                    mutableStateOf(1),
                    R.drawable.lemon_aioli,
                    "BurgerFuel Lemon Aioli",
                    "Free Range BurgerFuel Lemon Aioli",
                    1.80,
                    setOf("Sauces")
                ),

                MenuItem(
                    49,
                    mutableStateOf(1),
                    R.drawable.chipotle_aioli,
                    "BurgerFuel Chipotle Aioli",
                    "Free Range BurgerFuel Chipotle Aioli",
                    1.80,
                    setOf("Sauces")
                ),

                MenuItem(
                    50,
                    mutableStateOf(1),
                    R.drawable.vegan_aioli,
                    "BurgerFuel Vegan Aioli",
                    "BurgerFuel Vegan Aioli",
                    1.80,
                    setOf("Sauces")
                ),

                MenuItem(
                    51,
                    mutableStateOf(1),
                    R.drawable.tomato_sauce,
                    "Tomato Sauce",
                    "Tomato Sauce",
                    1.80,
                    setOf("Sauces")
                ),

            )
        }
    }

    override fun getIdentifier(): Int {
        return id;
    }
}