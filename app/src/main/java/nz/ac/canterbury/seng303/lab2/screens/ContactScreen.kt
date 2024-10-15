package nz.ac.canterbury.seng303.lab2.screens

import androidx.compose.foundation.clickable
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import nz.ac.canterbury.seng303.lab2.R

@Composable
fun ContactScreen(
    navController: NavController
)
{
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF4A2F7f), // Purple Shade
            Color(0xFF0ABEF0) // Blue Shade
        ),
        start = Offset(0f, 1000f),
        end = Offset(1000f, 0f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        //Title
        Text(
            "Contact Us",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // First Name & Last Name Row
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First name*") },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last name") },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
        }

        // Email Field
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email*") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        // Message Field
        TextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("What can we help you with?") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(4.dp),
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    data = Uri.parse("mailto:info@burgerfuel.com")
                    putExtra(Intent.EXTRA_SUBJECT, "Text")
                }
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.burger_fuel_purple), /* Background color of the button */
                contentColor = Color.White /* Color of the text in the button */
            )
//                .background(gradientBrush)
        ) {
            Text("SUBMIT", fontSize = 16.sp)
        }

//        Text(
//            text = "Or",
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center
//        )

        // Button UI with gradient background
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(4.dp)
//                .height(48.dp)
//                .background(
//                    brush = gradientBrush,
//                    shape = RoundedCornerShape(12.dp)
//                )
//                .clickable {
//                    // Intent to open a website
//                    val url = Uri.parse("https://www.burgerfuel.com/nz/contact-us")
//                    val browserSelectorIntent = Intent()
//                        .setAction(Intent.ACTION_VIEW)
//                        .addCategory(Intent.CATEGORY_BROWSABLE)
//                        .setData(Uri.parse("http:"))
//                    val targetIntent = Intent()
//                        .setAction(Intent.ACTION_VIEW)
//                        .addCategory(Intent.CATEGORY_BROWSABLE)
//                        .setData(url)
//
//                    targetIntent.selector = browserSelectorIntent
//
//                    context.startActivity(targetIntent)
//                },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = "Open BurgerFuel Website",
//                fontSize = 16.sp,
//                color = Color.White
//            )
//        }
    }

}