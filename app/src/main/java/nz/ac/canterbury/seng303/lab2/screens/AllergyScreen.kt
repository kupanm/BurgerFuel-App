package nz.ac.canterbury.seng303.lab2.screens

import androidx.compose.foundation.clickable
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.background
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nz.ac.canterbury.seng303.lab2.R

@Composable
fun AllergyScreen(
    navController: NavController
){
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.burger_fuel_purple), // Purple Shade
            colorResource(id = R.color.teal_500) // Blue Shade
        ),
        start = Offset(0f, 1000f),
        end = Offset(1000f, 0f)
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Title
        Text(
            text = "LIFESTYLE & ALLERGEN INFO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Subtitle
        Text(
            text = "EATING WITH ALLERGIES",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = "For your safety, please make informed decisions when eating with allergies by referring to our Allergen Information Chart before you eat with us. Our menu contains several known allergens, and we cannot 100% guarantee against cross contamination. Click on the Allergen Information link below to learn more and we also highly recommend you inform staff if you have any allergies or specific dietary needs prior to ordering.",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .height(48.dp)
                .background(colorResource(id = R.color.teal_500),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    // Intent to open a website
                    val url = Uri.parse("https://www.burgerfuel.com/uploads/media/66e377638b66e/allergen-dietary-suitability-chart-13-09-2024.pdf")
                    val browserSelectorIntent = Intent()
                        .setAction(Intent.ACTION_VIEW)
                        .addCategory(Intent.CATEGORY_BROWSABLE)
                        .setData(Uri.parse("http:"))
                    val targetIntent = Intent()
                        .setAction(Intent.ACTION_VIEW)
                        .addCategory(Intent.CATEGORY_BROWSABLE)
                        .setData(url)

                    targetIntent.selector = browserSelectorIntent

                    context.startActivity(targetIntent)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ALLERGEN INFORMATION",
                fontSize = 24.sp,
                color = colorResource(id = R.color.white)
            )
        }

        // Subtitle
        Text(
            text = "GLUTEN FREE",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = "BurgerFuel offers a gluten free bun that is wheat free, soy free and dairy free. Some of our burgers can be made gluten free – just chat with our staff. Click on the Allergen Information link above to learn about what products contain gluten.",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Subtitle
        Text(
            text = "VEGAN & VEGETARIAN",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = "Our vegetarian and vegan patties are specially cooked on a separate surface, and we reckon our Plant-Powered offerings are so good they’ll make even your most carnivorous mates jealous. Click on the Allergen Information link above to learn about what products are vegan and vegetarian suitable.",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Subtitle
        Text(
            text = "HALAL",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = "Please let our crew know if you are Halal and we’re happy to cook your favourite burger separately using fresh, clean utensils. Please be aware that our stores and food are not halal in the strictest sense as some products on our menu are not Halal certified and we cannot 100% guarantee against cross contamination with non-Halal products. Click on the Allergen Information link above to find out what products are Halal suitable.",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Subtitle
        Text(
            text = "LOW CARB",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = "Living life in the low-carb zone? Order any gourmet burger as a Low-Carborator to ditch the buns for a double hit of fresh iceberg lettuce and salad ingredients. Turn your burger experience into a fresh turbo-salad-charged taste explosion.",
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}
