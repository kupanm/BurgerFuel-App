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
            text = context.getString(R.string.allergen_info_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Subtitle
        Text(
            text = context.getString(R.string.eating_w_allergies),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = context.getString(R.string.eating_w_allergies_info),
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
                text = context.getString(R.string.allergen_info_title),
                fontSize = 24.sp,
                color = colorResource(id = R.color.white)
            )
        }

        // Subtitle
        Text(
            text = context.getString(R.string.gluten_free_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = context.getString(R.string.gluten_free_info),
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Subtitle
        Text(
            text = context.getString(R.string.vegan_vegetarian_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = context.getString(R.string.vegan_vegetarian_info),
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Subtitle
        Text(
            text = context.getString(R.string.halal_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = context.getString(R.string.halal_info),
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Subtitle
        Text(
            text = context.getString(R.string.low_carb_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
            text = context.getString(R.string.low_carb_info),
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}
