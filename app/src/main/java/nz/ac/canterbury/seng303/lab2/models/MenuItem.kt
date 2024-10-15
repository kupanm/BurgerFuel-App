package nz.ac.canterbury.seng303.lab2.models

import android.content.Context
import android.icu.util.Currency
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import nz.ac.canterbury.seng303.lab2.R

class MenuItem(
    val id: Int, /* Unique identifier for the menu item */
    var amount: Int, /* Amount of items for cart storage */
    val icon: Int, /* This is the image for the menu item. Always displayed */
    val name: String, /* This is the name for the menu item. Always displayed */
    val description: String, /* This is the description. Only shown when user clicks on menu item */
    val price: Double, /* This is the price of the menu item. Shown on the preview of a menu item and when user clicks on menu item. MAYBE CHANGE TYPE TO CURRENCY*/
    val type: Set<String>, /* This is the type of menu item it is. Special, NZ Beef, Free Range Chicken etc. */
): Identifiable {

    fun inc() {
        this.amount++
    }

    fun dec() {
        if(this.amount > 1) {
            this.amount--
        }
    }

    companion object {
        fun getMenuItems(context: Context): List<MenuItem> {
            return listOf(
                MenuItem(
                    1,
                    1,
                    R.drawable.ranch_feed_combo,
                    context.getString(R.string.ranch_feed_name),
                    context.getString(R.string.ranch_feed_description),
                    20.80,
                    setOf("Specials")
                ),

                MenuItem(
                    2,
                    1,
                    R.drawable.rebel_feed_combo,
                    context.getString(R.string.rebel_feed_name),
                    context.getString(R.string.rebel_feed_description),
                    26.50,
                    setOf("Specials")
                ),

                MenuItem(
                    3,
                    1,
                    R.drawable.ranch_wrangler_burger,
                    context.getString(R.string.ranch_wrangler_name),
                    context.getString(R.string.ranch_wrangler_description),
                    13.90,
                    setOf("Specials", "Plant-Powered", "Minis")
                ),

                MenuItem(
                    4,
                    1,
                    R.drawable.iron_rebel_burger,
                    context.getString(R.string.iron_rebel_name),
                    context.getString(R.string.iron_rebel_description),
                    21.50,
                    setOf("Specials", "NZ Beef")
                ),

                MenuItem(
                    5,
                    1,
                    R.drawable.chev_brulee_drink,
                    context.getString(R.string.chev_brulee_name),
                    context.getString(R.string.chev_brulee_description),
                    9.50,
                    setOf("Specials")
                ),

                MenuItem(
                    6,
                    1,
                    R.drawable.loaded_fries,
                    context.getString(R.string.off_roader_fries_name),
                    context.getString(R.string.off_roader_fries_description),
                    9.80,
                    setOf("Specials", "Sides")
                ),

                MenuItem(
                    7,
                    1,
                    R.drawable.muscle_single,
                    context.getString(R.string.muscle_single_name),
                    context.getString(R.string.muscle_single_description),
                    14.00,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    8,
                    1,
                    R.drawable.muscle_double,
                    context.getString(R.string.muscle_double_name),
                    context.getString(R.string.muscle_double_description),
                    19.50,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    9,
                    1,
                    R.drawable.c_n_cheese,
                    context.getString(R.string.c_n_cheese_name),
                    context.getString(R.string.c_n_cheese_description),
                    16.00,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    10,
                    1,
                    R.drawable.bio_fuel,
                    context.getString(R.string.bio_fuel_name),
                    context.getString(R.string.bio_fuel_description),
                    16.00,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    11,
                    1,
                    R.drawable.bastard,
                    context.getString(R.string.bastard_name),
                    context.getString(R.string.bastard_description),
                    19.50,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    12,
                    1,
                    R.drawable.ford_freakout,
                    context.getString(R.string.ford_freakout_name),
                    context.getString(R.string.ford_freakout_description),
                    16.60,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    13,
                    1,
                    R.drawable.bacon_bbq_roadster,
                    context.getString(R.string.bbq_roadster_name),
                    context.getString(R.string.bbq_roadster_description),
                    19.50,
                    setOf("NZ Beef")
                ),

                MenuItem(
                    14,
                    1,
                    R.drawable.hamburgini_w_cheese,
                    context.getString(R.string.hamburgini_name),
                    context.getString(R.string.hamburgini_description),
                    8.90,
                    setOf("NZ Beef", "Minis")
                ),

                MenuItem(
                    15,
                    1,
                    R.drawable.bacon_backfire,
                    context.getString(R.string.bacon_backfire_name),
                    context.getString(R.string.bacon_backfire_description),
                    17.40,
                    setOf("Free Range Chicken")
                ),

                MenuItem(
                    16,
                    1,
                    R.drawable.burnout,
                    context.getString(R.string.burnout_name),
                    context.getString(R.string.burnout_description),
                    17.60,
                    setOf("Free Range Chicken")
                ),

                MenuItem(
                    17,
                    1,
                    R.drawable.modifried_thunderbird,
                    context.getString(R.string.thunderbird_name),
                    context.getString(R.string.thunderbird_description),
                    17.40,
                    setOf("Free Range Chicken")
                ),

                MenuItem(
                    18,
                    1,
                    R.drawable.chook_royale,
                    context.getString(R.string.chook_royale_name),
                    context.getString(R.string.chook_royale_description),
                    10.50,
                    setOf("Free Range Chicken", "Minis")
                ),

                MenuItem(
                    19,
                    1,
                    R.drawable.v8_vegan,
                    context.getString(R.string.v8_vegan_name),
                    context.getString(R.string.v8_vegan_description),
                    15.90,
                    setOf("Plant-Powered")
                ),

                MenuItem(
                    20,
                    1,
                    R.drawable.v_twin_vege,
                    context.getString(R.string.v_twin_vege_name),
                    context.getString(R.string.v_twin_vege_description),
                    14.90,
                    setOf("Plant-Powered")
                ),

                MenuItem(
                    21,
                    1,
                    R.drawable.spud_fries_aioli,
                    context.getString(R.string.spud_fries_aioli_name),
                    context.getString(R.string.spud_fries_aioli_description),
                    6.90,
                    setOf("Sides")
                ),

                MenuItem(
                    22,
                    1,
                    R.drawable.kumara_fries_aioli,
                    context.getString(R.string.kumara_fries_aioli_name),
                    context.getString(R.string.kumara_fries_aioli_description),
                    7.90,
                    setOf("Sides")
                ),

                MenuItem(
                    23,
                    1,
                    R.drawable.smash_browns_aioli,
                    context.getString(R.string.smash_browns_aioli_name),
                    context.getString(R.string.smash_browns_aioli_description),
                    7.90,
                    setOf("Sides")
                ),

                MenuItem(
                    24,
                    1,
                    R.drawable.choppers,
                    context.getString(R.string.choppers_name),
                    context.getString(R.string.choppers_description),
                    12.90,
                    setOf("Sides")
                ),

                MenuItem(
                    25,
                    1,
                    R.drawable.chicken_fenders,
                    context.getString(R.string.chicken_fenders_name),
                    context.getString(R.string.chicken_fenders_description),
                    10.90,
                    setOf("Sides")
                ),

                MenuItem(
                    26,
                    1,
                    R.drawable.motobites,
                    context.getString(R.string.motobites_name),
                    context.getString(R.string.motobites_description),
                    8.90,
                    setOf("Sides")
                ),

                MenuItem(
                    27,
                    1,
                    R.drawable.thickshake,
                    context.getString(R.string.thickshake_name),
                    context.getString(R.string.thickshake_description),
                    8.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    28,
                    1,
                    R.drawable.soy_thickshake,
                    context.getString(R.string.soy_thickshake_name),
                    context.getString(R.string.soy_thickshake_description),
                    9.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    29,
                    1,
                    R.drawable.rescued_cherry_cola,
                    context.getString(R.string.rescued_cherry_cola_name),
                    context.getString(R.string.rescued_cherry_cola_description),
                    5.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    30,
                    1,
                    R.drawable.rescued_cherry_lemonade,
                    context.getString(R.string.rescued_cherry_lemonade_name),
                    context.getString(R.string.rescued_cherry_lemonade_description),
                    5.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    31,
                    1,
                    R.drawable.rescued_orangeade,
                    context.getString(R.string.rescued_orangeade_name),
                    context.getString(R.string.rescued_orangeade_description),
                    5.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    32,
                    1,
                    R.drawable.coke_330ml,
                    context.getString(R.string.coke_330ml_name),
                    context.getString(R.string.coke_330ml_description),
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    33,
                    1,
                    R.drawable.coke_zero_sugar_330ml,
                    context.getString(R.string.coke_zero_sugar_330ml_name),
                    context.getString(R.string.coke_zero_sugar_330ml_description),
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    34,
                    1,
                    R.drawable.diet_coke_330ml,
                    context.getString(R.string.diet_coke_330ml_name),
                    context.getString(R.string.diet_coke_330ml_description),
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    35,
                    1,
                    R.drawable.sprite_330ml,
                    context.getString(R.string.sprite_330ml_name),
                    context.getString(R.string.sprite_330ml_description),
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    36,
                    1,
                    R.drawable.l_n_p_330ml,
                    context.getString(R.string.l_n_p_330ml_name),
                    context.getString(R.string.l_n_p_330ml_description),
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    37,
                    1,
                    R.drawable.fanta_330ml,
                    context.getString(R.string.fanta_330ml_name),
                    context.getString(R.string.fanta_330ml_description),
                    5.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    38,
                    1,
                    R.drawable.organic_apple_orange_mango,
                    context.getString(R.string.organic_apple_orange_mango_name),
                    context.getString(R.string.organic_apple_orange_mango_description),
                    5.90,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    39,
                    1,
                    R.drawable.organic_apple_feijoa,
                    context.getString(R.string.organic_apple_feijoa_name),
                    context.getString(R.string.organic_apple_feijoa_description),
                    5.90,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    40,
                    1,
                    R.drawable.organic_apple_blackcurrant,
                    context.getString(R.string.organic_apple_blackcurrant_name),
                    context.getString(R.string.organic_apple_blackcurrant_description),
                    5.90,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    41,
                    1,
                    R.drawable.kiwi_blue600ml,
                    context.getString(R.string.kiwi_blue_600ml_name),
                    context.getString(R.string.kiwi_blue_600ml_description),
                    5.70,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    42,
                    1,
                    R.drawable.keri_kids_apple,
                    context.getString(R.string.keri_kids_apple_name),
                    context.getString(R.string.keri_kids_apple_description),
                    3.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    43,
                    1,
                    R.drawable.keri_kids_apple_blackcurrant,
                    context.getString(R.string.keri_kids_apple_blackcurrant_name),
                    context.getString(R.string.keri_kids_apple_blackcurrant_description),
                    3.00,
                    setOf("Thickshakes & Drinks")
                ),

                MenuItem(
                    44,
                    1,
                    R.drawable.kids_ink_cheeseburger_meal,
                    context.getString(R.string.kids_ink_cheeseburger_meal_name),
                    context.getString(R.string.kids_ink_cheeseburger_meal_description),
                    13.50,
                    setOf("Kids Ink. Meals")
                ),

                MenuItem(
                    45,
                    1,
                    R.drawable.kids_ink_mini_motobites_meal,
                    context.getString(R.string.kids_ink_mini_motobites_meal_name),
                    context.getString(R.string.kids_ink_mini_motobites_meal_description),
                    13.50,
                    setOf("Kids Ink. Meals")
                ),

                MenuItem(
                    46,
                    1,
                    R.drawable.kids_ink_teeny_choppers_meal,
                    context.getString(R.string.kids_ink_teeny_choppers_meal_name),
                    context.getString(R.string.kids_ink_teeny_choppers_meal_description),
                    13.50,
                    setOf("Kids Ink. Meals")
                ),

                MenuItem(
                    47,
                    1,
                    R.drawable.aioli,
                    context.getString(R.string.burgerfuel_aioli_name),
                    context.getString(R.string.burgerfuel_aioli_description),
                    1.80,
                    setOf("Sauces")
                ),

                MenuItem(
                    48,
                    1,
                    R.drawable.lemon_aioli,
                    context.getString(R.string.burgerfuel_lemon_aioli_name),
                    context.getString(R.string.burgerfuel_lemon_aioli_description),
                    1.80,
                    setOf("Sauces")
                ),

                MenuItem(
                    49,
                    1,
                    R.drawable.chipotle_aioli,
                    context.getString(R.string.burgerfuel_chipotle_aioli_name),
                    context.getString(R.string.burgerfuel_chipotle_aioli_description),
                    1.80,
                    setOf("Sauces")
                ),

                MenuItem(
                    50,
                    1,
                    R.drawable.vegan_aioli,
                    context.getString(R.string.burgerfuel_vegan_aioli_name),
                    context.getString(R.string.burgerfuel_vegan_aioli_description),
                    1.80,
                    setOf("Sauces")
                ),

                MenuItem(
                    51,
                    1,
                    R.drawable.tomato_sauce,
                    context.getString(R.string.tomato_sauce_name),
                    context.getString(R.string.tomato_sauce_description),
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